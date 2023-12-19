package com.bharat.noteskmp.data.repository

import Note
import com.bharat.noteskmp.data.model.UserEntity
import com.bharat.noteskmp.utils.StringConstants.CONNECTION_STRING_URL
import com.bharat.noteskmp.utils.StringConstants.DATABASE_NAME
import com.bharat.noteskmp.utils.StringConstants.NOTES_COLLECTION_NAME
import com.bharat.noteskmp.utils.StringConstants.USER_COLLECTION_NAME
import com.mongodb.client.model.Filters.lt
import com.mongodb.kotlin.client.coroutine.MongoClient
import data.respository.NotesRepository
import kotlinx.coroutines.flow.toList

class NotesRepositoryImpl(

) : NotesRepository {
    private val uri = CONNECTION_STRING_URL
    private val mongoClient = MongoClient.create(uri)
    private val database = mongoClient.getDatabase(DATABASE_NAME)
    private val notesCollection = database.getCollection<Note>(NOTES_COLLECTION_NAME)
    val userCollection = database.getCollection<UserEntity>(USER_COLLECTION_NAME)
    override suspend fun addNote(note: Note): Boolean {
        return notesCollection.insertOne(
              note
            ).wasAcknowledged()
    }

    override suspend fun getNotesPerUserId(userId: String): List<Note> {
       return notesCollection.withDocumentClass<Note>()
            .find(lt(Note::userId.name, userId)).toList()
    }
}