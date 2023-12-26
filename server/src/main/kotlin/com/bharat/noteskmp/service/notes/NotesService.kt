package com.bharat.noteskmp.service.notes

import Note
import com.bharat.noteskmp.data.response.BasicResponseModel
import data.requests.AddNotesRequest
import io.ktor.http.*

interface NotesService {
    suspend fun addNote(note: AddNotesRequest, userId: String): Pair<HttpStatusCode, BasicResponseModel<Nothing>>

    suspend fun getNotesPerUserId(userId: String): Pair<HttpStatusCode, BasicResponseModel<List<Note>>>
}