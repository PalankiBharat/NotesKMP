package com.bharat.noteskmp.service.notes

import Note
import com.bharat.noteskmp.data.response.BasicResponseModel
import data.requests.AddNotesRequest
import data.requests.EditNotesRequest
import io.ktor.http.HttpStatusCode

interface NotesService {

    suspend fun addNote(note: AddNotesRequest, userId: String): Pair<HttpStatusCode, BasicResponseModel<Nothing>>

    suspend fun getNotesPerUserId(userId: String): Pair<HttpStatusCode, BasicResponseModel<List<Note>>>

    suspend fun getNoteById(noteId:String): Pair<HttpStatusCode, BasicResponseModel<Note>>

    suspend fun deleteNote(note: String): Pair<HttpStatusCode, BasicResponseModel<Nothing>>

    suspend fun editNote(note: EditNotesRequest, userId: String):  Pair<HttpStatusCode, BasicResponseModel<Note>>


}