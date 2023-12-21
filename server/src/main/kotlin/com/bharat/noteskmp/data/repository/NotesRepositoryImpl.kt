package com.bharat.noteskmp.data.repository

import Note
import com.mongodb.client.model.Filters.lt
import com.mongodb.kotlin.client.coroutine.MongoCollection
import data.respository.notes.NotesRepository
import kotlinx.coroutines.flow.toList

class NotesRepositoryImpl(
    private val notesCollection: MongoCollection<Note>
) : NotesRepository {
    override suspend fun addNote(note: Note): Boolean {
        return notesCollection.insertOne(
            note
        ).wasAcknowledged()
    }

    override suspend fun getNotesPerUserId(userId: String): List<Note> {
        return notesCollection.withDocumentClass<Note>().find(lt(Note::userId.name, userId)).toList()
    }
}