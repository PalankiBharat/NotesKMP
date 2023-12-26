package com.bharat.noteskmp.service.auth

import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.security.token.TokenConfig
import data.requests.LoginRequest
import data.requests.SignupRequest
import data.response.LoginResponse
import io.ktor.http.*

interface AuthService {
    suspend fun signup(signupRequest: SignupRequest): Pair<HttpStatusCode, BasicResponseModel<Nothing>>

    suspend fun login(
        loginRequest: LoginRequest,
        tokenConfig: TokenConfig
    ): Pair<HttpStatusCode, BasicResponseModel<LoginResponse>>
}