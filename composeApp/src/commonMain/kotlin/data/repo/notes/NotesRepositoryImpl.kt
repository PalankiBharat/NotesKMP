package data.repo.notes

import Note
import data.api.notes.NotesApiClient
import data.api.utils.ApiResult
import data.api.utils.safeApiCall
import data.models.BasicResponseModel
import kotlinx.coroutines.Dispatchers

class NotesRepositoryImpl(val notesApiService: NotesApiClient) : NotesRepository {
    override suspend fun getAllNotes(): ApiResult<List<Note>?> {
        return safeApiCall(Dispatchers.Default){
            notesApiService.getAllNotes()
        }
    }

    override suspend fun addNote(note: Note): ApiResult<BasicResponseModel<Nothing>> {
        TODO("Not yet implemented")
    }

    override suspend fun editNote(note: Note): ApiResult<BasicResponseModel<Nothing>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(note: Note): ApiResult<BasicResponseModel<Nothing>> {
        TODO("Not yet implemented")
    }
}