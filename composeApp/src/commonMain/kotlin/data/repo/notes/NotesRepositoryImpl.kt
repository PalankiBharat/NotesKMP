package data.repo.notes

import Note
import data.api.notes.NotesApiClient
import data.api.utils.ApiResult
import data.api.utils.safeApiCall
import data.models.BasicResponseModel
import data.requests.AddNotesRequest
import data.requests.EditNotesRequest
import kotlinx.coroutines.Dispatchers

class NotesRepositoryImpl(val notesApiService: NotesApiClient) : NotesRepository {
    override suspend fun getAllNotes(): ApiResult<List<Note>?> {
        return safeApiCall(Dispatchers.Default){
            notesApiService.getAllNotes()
        }
    }

    override suspend fun addNote(note: AddNotesRequest): ApiResult<Note?> {
       return safeApiCall(Dispatchers.Default)
       {
           notesApiService.addNote(note)
       }
    }

    override suspend fun editNote(note: EditNotesRequest): ApiResult<Note?> {
        return safeApiCall(Dispatchers.Default)
        {
            notesApiService.editNote(note)
        }
    }

    override suspend fun deleteNote(note: Note): ApiResult<BasicResponseModel<Nothing>> {
        TODO("Not yet implemented")
    }
}