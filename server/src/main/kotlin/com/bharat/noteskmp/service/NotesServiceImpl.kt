package com.bharat.noteskmp.service

import Note
import com.bharat.noteskmp.data.request.AddNotesRequest
import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.data.response.failureResponse
import com.bharat.noteskmp.data.response.successResponse
import com.bharat.noteskmp.utils.StringConstants
import com.bharat.noteskmp.utils.commonResult
import com.bharat.noteskmp.utils.internalServerErrorResult
import com.bharat.noteskmp.utils.okResult
import data.respository.NotesRepository
import io.ktor.http.HttpStatusCode
import org.bson.types.ObjectId

class NotesServiceImpl(
    val notesRepository: NotesRepository
) : NotesService {
    override suspend fun addNote(note: AddNotesRequest): Pair<HttpStatusCode, BasicResponseModel<Nothing>> {
        return try {
            val isNotesAdded = notesRepository.addNote(
                Note(
                    id = ObjectId().toHexString(),
                    title = note.title,
                    userId = note.userId,
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
        } catch (e: Exception) {
            internalServerErrorResult()
        }
    }

    override suspend fun getNotesPerUserId(userId: String): Pair<HttpStatusCode, BasicResponseModel<List<Note>>> {
        return try {
            if (userId.isBlank()) {
                commonResult(HttpStatusCode.BadRequest, failureResponse<List<Note>>("User Id cannot be blank"))
            } else {
                val resultsFlow = notesRepository.getNotesPerUserId(userId)
                okResult(successResponse(data = resultsFlow, message = "List Fetched Successfully"))
            }
        } catch (e: Exception) {
            internalServerErrorResult()
        }
    }
}