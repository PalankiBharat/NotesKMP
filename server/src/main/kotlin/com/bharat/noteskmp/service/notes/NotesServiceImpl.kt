package com.bharat.noteskmp.service.notes

import Note
import com.bharat.noteskmp.data.repository.NotesRepository
import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.data.response.failureResponse
import com.bharat.noteskmp.data.response.successResponse
import com.bharat.noteskmp.utils.StringConstants
import com.bharat.noteskmp.utils.commonResult
import com.bharat.noteskmp.utils.internalServerErrorResult
import com.bharat.noteskmp.utils.okResult
import com.bharat.noteskmp.utils.safeServerCall
import data.requests.AddNotesRequest
import data.requests.EditNotesRequest
import io.ktor.http.HttpStatusCode
import org.bson.types.ObjectId


class NotesServiceImpl(
    val notesRepository: NotesRepository
) : NotesService {
    override suspend fun addNote(
        note: AddNotesRequest,
        userId: String
    ): Pair<HttpStatusCode, BasicResponseModel<Nothing>> {
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

    override suspend fun getNoteById(noteId: String): Pair<HttpStatusCode, BasicResponseModel<Note>> {
        return safeServerCall {
            val note = notesRepository.getNoteById(noteId)
            if (note != null) {
                okResult(successResponse(message = "Note Found", data = note))
            } else {
                okResult(failureResponse(errorMessage = "No Data Found"))
            }
        }
    }

    override suspend fun deleteNote(noteId: String): Pair<HttpStatusCode, BasicResponseModel<Nothing>> {
        return safeServerCall {
            val wasDeleted = notesRepository.deleteNote(noteId = noteId)
            if (wasDeleted) {
                okResult(successResponse(message = "Note Successfully Deleted", data = null))
            } else {
                okResult(failureResponse(errorMessage = "Note was not deleted Please try again"))
            }
        }
    }

    override suspend fun editNote(
        note: EditNotesRequest,
        userId: String
    ): Pair<HttpStatusCode, BasicResponseModel<Note>> {
        return safeServerCall {
            val oldNote = notesRepository.getNoteById(noteId = note.id)
            if (oldNote == null) {
                commonResult(HttpStatusCode.BadRequest, failureResponse("Bad Request"))
            } else {
                val newNote = Note(
                    id = oldNote.id,
                    title = note.title,
                    userId = userId,
                    description = note.description,
                    dateCreated = oldNote.dateCreated,
                    dateUpdated = System.currentTimeMillis(),
                )
                val wasEdited = notesRepository.editNote(note = newNote)
                if (wasEdited) {
                    okResult(successResponse(message = "Note Successfully Edited", data = newNote))
                } else {
                    okResult(failureResponse(errorMessage = "Note was not Edited Please try again"))
                }
            }
        }
    }
}