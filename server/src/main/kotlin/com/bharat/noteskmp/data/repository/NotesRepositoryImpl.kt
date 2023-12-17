package com.bharat.noteskmp.data.repository

import Note
import data.models.User
import com.bharat.noteskmp.data.request.AddNotesRequest
import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.data.response.failureResponse
import com.bharat.noteskmp.data.response.successResponse
import com.bharat.noteskmp.utils.StringConstants.BASIC_ERROR_MESSAGE
import com.bharat.noteskmp.utils.StringConstants.CONNECTION_STRING_URL
import com.bharat.noteskmp.utils.StringConstants.DATABASE_NAME
import com.bharat.noteskmp.utils.StringConstants.NOTES_COLLECTION_NAME
import com.bharat.noteskmp.utils.StringConstants.USER_COLLECTION_NAME
import com.mongodb.client.model.Filters.lt
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class NotesRepositoryImpl(

) : NotesRepository {
    private val uri = CONNECTION_STRING_URL
    private val mongoClient = MongoClient.create(uri)
    private val database = mongoClient.getDatabase(DATABASE_NAME)
    private val notesCollection = database.getCollection<Note>(NOTES_COLLECTION_NAME)
    val userCollection = database.getCollection<User>(USER_COLLECTION_NAME)
    override suspend fun addNote(note: AddNotesRequest): BasicResponseModel<Nothing> {
        return try {
            val isNotesAdded = notesCollection.insertOne(
                Note(
                    id = ObjectId().toHexString(),
                    title = note.title,
                    userId = note.userId,
                    description = note.description,
                    dateCreated = System.currentTimeMillis(),
                    dateUpdated = 0L,
                )
            ).wasAcknowledged()
            if (!isNotesAdded) {
                failureResponse(BASIC_ERROR_MESSAGE)
            } else {
                successResponse(data = null, message = "Note Added Successfully")
            }
        } catch (e: Exception) {
            failureResponse(BASIC_ERROR_MESSAGE)
        }
    }

    override suspend fun getNotesPerUserId(userId: String): BasicResponseModel<List<Note>> {
        return try {
            val resultsFlow = notesCollection.withDocumentClass<Note>()
                .find(lt(Note::userId.name, userId)).toList()
            successResponse(data = resultsFlow, message = "List Fetched Successfully")
        }catch (e: Exception) {
            e.message
            failureResponse(BASIC_ERROR_MESSAGE)
        }
    }
}