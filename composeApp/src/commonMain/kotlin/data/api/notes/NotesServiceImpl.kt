package data.api.notes

import Note
import data.api.HttpClient
import data.models.BasicResponseModel
import data.requests.AddNotesRequest
import data.requests.EditNotesRequest
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class NotesServiceImpl(private val client: HttpClient) : NotesApiClient {

    override suspend fun getAllNotes(): BasicResponseModel<List<Note>> {
        val response = client.httpClient.get("note")
        return response.body()
    }

    override suspend fun addNote(addNoteRequest: AddNotesRequest): BasicResponseModel<Note> {
        val response = client.httpClient.post("note"){
            contentType(ContentType.Application.Json)
            setBody(addNoteRequest)
        }
        return response.body()
    }

    override suspend fun editNote(updateNotesRequest: EditNotesRequest): BasicResponseModel<Note> {
        val response = client.httpClient.post("note"){
            contentType(ContentType.Application.Json)
            setBody(updateNotesRequest)
        }
        return response.body()
    }

    override suspend fun deleteNote(noteId: String): BasicResponseModel<Nothing> {
        val response = client.httpClient.delete("note"){
            contentType(ContentType.Application.Json)
            setBody(noteId)
        }
        return response.body()
    }


}