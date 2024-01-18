package com.bharat.noteskmp.service.auth

import com.bharat.noteskmp.data.repository.AuthRepository
import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.data.response.failureResponse
import com.bharat.noteskmp.data.response.successResponse
import com.bharat.noteskmp.route.RouteConstants.User.USER_ID
import com.bharat.noteskmp.security.token.TokenClaim
import com.bharat.noteskmp.security.token.TokenConfig
import com.bharat.noteskmp.security.token.TokenService
import com.bharat.noteskmp.utils.StringConstants
import com.bharat.noteskmp.utils.commonResult
import com.bharat.noteskmp.utils.internalServerErrorResult
import com.bharat.noteskmp.utils.okResult
import data.requests.LoginRequest
import data.requests.SignupRequest
import data.response.LoginResponse
import io.ktor.http.*
import org.koin.core.component.KoinComponent

class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val tokenService: TokenService,
) : AuthService, KoinComponent {
    override suspend fun signup(signupRequest: SignupRequest, tokenConfig: TokenConfig): Pair<HttpStatusCode, BasicResponseModel<LoginResponse>> {
        return try {
            val isUserExist = authRepository.findUserOrNull(signupRequest.email) != null
            if (isUserExist) {
                okResult(failureResponse("User Already Exists. Login to Continue"))
            } else {
                val isSignedUpSuccessful = authRepository.signUpUser(
                    signupRequest
                )
                if (isSignedUpSuccessful) {
                    login(LoginRequest(signupRequest.email,signupRequest.password),tokenConfig)
                } else {
                    internalServerErrorResult()
                }
            }
        } catch (e: Exception) {
            internalServerErrorResult()
        }
    }

    override suspend fun login(
        loginRequest: LoginRequest,
        tokenConfig: TokenConfig
    ): Pair<HttpStatusCode, BasicResponseModel<LoginResponse>> {
        return try {
            val user = authRepository.findUserOrNull(loginRequest.email)
            val isUserExist = user != null
            if (isUserExist) {
                val canLogin = authRepository.loginUser(loginRequest)
                val token = tokenService.generate(config = tokenConfig, TokenClaim(name = USER_ID, value = user?.id.toString()))
                if (canLogin) {
                    okResult(successResponse(data = LoginResponse(token = token), message = "Signed Up Successfully"))
                } else {
                    okResult(failureResponse(StringConstants.BASIC_ERROR_MESSAGE))
                }
            } else {
                commonResult(HttpStatusCode.Conflict, failureResponse("User does not exist. Please continue with Signup"))
            }
        } catch (e: Exception) {
            internalServerErrorResult()
        }
    }


}