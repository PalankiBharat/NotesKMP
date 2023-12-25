package com.bharat.noteskmp.data.repository

import Note

interface NotesRepository {
    suspend fun addNote(note: Note): Boolean

    suspend fun getNotesPerUserId(userId:String): List<Note>
}