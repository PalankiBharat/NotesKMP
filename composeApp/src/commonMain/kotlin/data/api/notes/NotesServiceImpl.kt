package data.api.notes

import Note
import data.api.HttpClient
import data.models.BasicResponseModel
import io.ktor.client.call.body
import io.ktor.client.request.get

class NotesServiceImpl(private val client: HttpClient) : NotesApiClient {

    override suspend fun getAllNotes(): BasicResponseModel<List<Note>> {
        val response = client.httpClient.get("note")
        return response.body()
    }


}