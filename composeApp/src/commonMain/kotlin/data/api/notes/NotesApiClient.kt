package data.api.notes

import Note
import data.models.BasicResponseModel
import data.requests.AddNotesRequest
import data.requests.EditNotesRequest

interface NotesApiClient {
    suspend fun getAllNotes(): BasicResponseModel<List<Note>>
    suspend fun addNote(addNoteRequest :AddNotesRequest): BasicResponseModel<Note>
    suspend fun editNote(updateNotesRequest: EditNotesRequest): BasicResponseModel<Note>
    suspend fun deleteNote( noteId: String): BasicResponseModel<Nothing>

}