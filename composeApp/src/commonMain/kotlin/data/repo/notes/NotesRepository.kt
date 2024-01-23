package data.repo.notes

import Note
import data.api.utils.ApiResult
import data.models.BasicResponseModel

interface NotesRepository {
    suspend fun getAllNotes(): ApiResult<List<Note>?>

    suspend fun addNote(note: Note): ApiResult<BasicResponseModel<Nothing>>
    suspend fun editNote(note: Note): ApiResult<BasicResponseModel<Nothing>>
    suspend fun deleteNote(note: Note): ApiResult<BasicResponseModel<Nothing>>
}