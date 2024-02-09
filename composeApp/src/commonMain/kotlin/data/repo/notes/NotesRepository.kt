package data.repo.notes

import Note
import data.api.utils.ApiResult
import data.models.BasicResponseModel
import data.requests.AddNotesRequest
import data.requests.EditNotesRequest

interface NotesRepository {
    suspend fun getAllNotes(): ApiResult<List<Note>?>

    suspend fun addNote(note: AddNotesRequest): ApiResult<Note?>
    suspend fun editNote(note: EditNotesRequest): ApiResult<Note?>
    suspend fun deleteNote(note: Note): ApiResult<BasicResponseModel<Nothing>>
}