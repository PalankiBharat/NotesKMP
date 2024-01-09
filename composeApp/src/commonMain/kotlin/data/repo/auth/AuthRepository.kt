package data.repo.auth

import data.api.utils.ApiResult
import data.models.BasicResponseModel
import data.requests.LoginRequest
import data.requests.SignupRequest
import data.response.LoginResponse

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): ApiResult<LoginResponse?>
    suspend fun signUp(signupRequest: SignupRequest): ApiResult<LoginResponse?>
}