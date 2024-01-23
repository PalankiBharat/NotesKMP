package data.api.auth

import data.api.HttpClient
import data.models.BasicResponseModel
import data.requests.LoginRequest
import data.requests.SignupRequest
import data.response.LoginResponse
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthServiceImpl(private val client: HttpClient) : AuthApiService {
    override suspend fun login(loginRequest: LoginRequest): BasicResponseModel<LoginResponse> {
        val response = client.httpClient.post("signin") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }
        return response.body()
    }

    override suspend fun signUp(signupRequest: SignupRequest): BasicResponseModel<LoginResponse> {
        val response = client.httpClient.post("signup") {
            contentType(ContentType.Application.Json)
            setBody(signupRequest)
        }
        return response.body()
    }


}