package data.api.auth

import data.models.BasicResponseModel
import data.requests.LoginRequest
import data.requests.SignupRequest
import data.response.LoginResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class AuthServiceImpl(private val client: HttpClient) : AuthApiService {
    override suspend fun login(loginRequest: LoginRequest): BasicResponseModel<LoginResponse> {
        val response = client.post("asdsd") {
            setBody(loginRequest)
        }
        return response.body()
    }

    override suspend fun signUp(signupRequest: SignupRequest): BasicResponseModel<String> {
        val response = client.post("asdsd") {
            setBody(signupRequest)
        }
        return response.body()    }
}