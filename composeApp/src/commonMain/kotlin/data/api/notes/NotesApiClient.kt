package data.api.notes

import Note
import data.models.BasicResponseModel

interface NotesApiClient {
    suspend fun getAllNotes(): BasicResponseModel<List<Note>>

}