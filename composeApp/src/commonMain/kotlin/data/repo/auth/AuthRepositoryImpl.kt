package data.repo.auth

import data.api.auth.AuthApiService
import data.api.utils.ApiResult
import data.api.utils.safeApiCall
import data.requests.LoginRequest
import data.requests.SignupRequest
import data.response.LoginResponse
import kotlinx.coroutines.Dispatchers

class AuthRepositoryImpl(val authApiService: AuthApiService) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): ApiResult<LoginResponse?> {
      return  safeApiCall(Dispatchers.Default)
        {
            authApiService.login(loginRequest)
        }

    }

    override suspend fun signUp(signupRequest: SignupRequest): ApiResult<String?> {
        return safeApiCall(Dispatchers.Default){
            authApiService.signUp(signupRequest)
        }
    }
}