package com.bharat.noteskmp.data.repository

import Note
import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.data.request.AddNotesRequest

interface NotesRepository {
    suspend fun addNote(note: Note): Boolean

    suspend fun getNotesPerUserId(userId:String): List<Note>
}