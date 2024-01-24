package com.bharat.noteskmp.data.repository

import Note
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.ReplaceOptions
import com.mongodb.kotlin.client.coroutine.MongoCollection
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
        return notesCollection.withDocumentClass<Note>().find(eq(Note::userId.name, userId))
            .toList()
    }

    override suspend fun getNoteById(noteId: String): Note? {
        return notesCollection.withDocumentClass<Note>().find(eq(Note::id.name, noteId)).toList()
            .firstOrNull()
    }

    override suspend fun deleteNote(noteId: String): Boolean {
        val query = eq(Note::id.name, noteId)
        return notesCollection.deleteOne(query).wasAcknowledged()
    }

    override suspend fun editNote(note: Note): Boolean {
        val query = eq(Note::id.name, note.id)
        val options = ReplaceOptions().upsert(true)
        val result = notesCollection.replaceOne(query, note, options)
        return result.wasAcknowledged()
    }
}