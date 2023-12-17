package com.bharat.noteskmp.service

import Note
import com.bharat.noteskmp.data.request.AddNotesRequest
import com.bharat.noteskmp.data.response.BasicResponseModel
import io.ktor.http.*

interface NotesService {
    suspend fun addNote(note: AddNotesRequest): Pair<HttpStatusCode, BasicResponseModel<Nothing>>

    suspend fun getNotesPerUserId(userId: String): Pair<HttpStatusCode, BasicResponseModel<List<Note>>>
}