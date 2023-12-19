package com.bharat.noteskmp.service.auth

import Note
import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.data.response.failureResponse
import com.bharat.noteskmp.data.response.successResponse
import com.bharat.noteskmp.utils.StringConstants
import com.bharat.noteskmp.utils.internalServerErrorResult
import com.bharat.noteskmp.utils.okResult
import data.requests.SignupRequest
import data.respository.AuthRepository
import io.ktor.http.*

class AuthServiceImpl(
    val authRepository: AuthRepository
) : AuthService {
    override suspend fun signup(signupRequest: SignupRequest): Pair<HttpStatusCode, BasicResponseModel<Nothing>> {
        return try {
            val isSignedUpSuccessful = authRepository.signUpUser(
                signupRequest
            )
            if (!isSignedUpSuccessful) {
                okResult(failureResponse(StringConstants.BASIC_ERROR_MESSAGE))
            } else {
                okResult(successResponse(data = null, message = "Signed Up Successfully"))
            }
        } catch (e: Exception) {
            internalServerErrorResult()
        }
    }

    override suspend fun login(userId: String): Pair<HttpStatusCode, BasicResponseModel<List<Note>>> {
        TODO("Not yet implemented")
    }


}