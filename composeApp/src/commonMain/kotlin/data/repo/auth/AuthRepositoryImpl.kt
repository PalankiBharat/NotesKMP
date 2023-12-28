package data.repo.auth

import data.api.auth.AuthApiService
import data.api.utils.ApiResult
import data.api.utils.safeApiCall
import data.requests.LoginRequest
import data.requests.SignupRequest
import data.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class AuthRepositoryImpl(val authApiService: AuthApiService) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): ApiResult<LoginResponse> {
        safeApiCall(Dispatchers.IO)
        {
            authApiService.login()
        }

    }

    override suspend fun signUp(signupRequest: SignupRequest): ApiResult<Nothing> {
        TODO("Not yet implemented")
    }
}