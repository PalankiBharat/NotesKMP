package data.api.notes

import Note
import data.models.BasicResponseModel
import data.requests.LoginRequest
import data.requests.SignupRequest
import data.response.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class NotesServiceImpl(private val client: HttpClient) : NotesApiClient {

    override suspend fun getAllNotes(): BasicResponseModel<List<Note>> {
        val response = client.post("signup") {
            contentType(ContentType.Application.Json)
            setBody(signupRequest)
        }
        return response.body()
    }


}