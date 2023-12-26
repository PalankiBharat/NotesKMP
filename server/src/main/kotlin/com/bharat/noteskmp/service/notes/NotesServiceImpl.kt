package com.bharat.noteskmp.service.notes

import Note
import com.bharat.noteskmp.data.repository.NotesRepository
import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.data.response.failureResponse
import com.bharat.noteskmp.data.response.successResponse
import com.bharat.noteskmp.utils.*
import data.requests.AddNotesRequest
import io.ktor.http.*
import org.bson.types.ObjectId


class NotesServiceImpl(
    val notesRepository: NotesRepository
) : NotesService {
    override suspend fun addNote(note: AddNotesRequest, userId: String): Pair<HttpStatusCode, BasicResponseModel<Nothing>> {
       return safeServerCall {
            val isNotesAdded = notesRepository.addNote(
                Note(
                    id = ObjectId().toHexString(),
                    title = note.title,
                    userId = userId,
                    description = note.description,
                    dateCreated = System.currentTimeMillis(),
                    dateUpdated = 0L,
                )
            )
            if (!isNotesAdded) {
                okResult(failureResponse(StringConstants.BASIC_ERROR_MESSAGE))
            } else {
                okResult(successResponse(data = null, message = "Note Added Successfully"))
            }
        }
    }

    override suspend fun getNotesPerUserId(userId: String): Pair<HttpStatusCode, BasicResponseModel<List<Note>>> {
        return try {
            if (userId.isBlank()) {
                commonResult(HttpStatusCode.BadRequest, failureResponse("User Id cannot be blank"))
            } else {
                val resultsFlow = notesRepository.getNotesPerUserId(userId)
                okResult(successResponse(data = resultsFlow, message = "List Fetched Successfully"))
            }
        } catch (e: Exception) {
            internalServerErrorResult()
        }
    }
}