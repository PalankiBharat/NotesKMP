package data.api.auth

import data.models.BasicResponseModel
import data.requests.LoginRequest
import data.requests.SignupRequest
import data.response.LoginResponse

interface AuthApiService {
    suspend fun login(loginRequest: LoginRequest):BasicResponseModel<LoginResponse>

    suspend fun signUp(signupRequest: SignupRequest):BasicResponseModel<String>
}