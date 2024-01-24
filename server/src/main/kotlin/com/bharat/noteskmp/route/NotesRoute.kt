package com.bharat.noteskmp.route

import com.bharat.noteskmp.service.notes.NotesService
import com.bharat.noteskmp.utils.sendResponse
import com.bharat.noteskmp.utils.userId
import data.requests.AddNotesRequest
import data.requests.EditNotesRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.notesRoute() {
    val notesService: NotesService by inject()
    route(RouteConstants.API_VERSION) {
        route(RouteConstants.Notes.ROUTE) {
            authenticate {
                post {
                    val userId = call.userId() ?: ""
                    val notesParam = call.receive<AddNotesRequest>()
                    val response = notesService.addNote(notesParam, userId)
                    call.sendResponse(response)
                }

                delete {
                    val noteId = call.parameters["id"]
                        ?: return@delete call.respond(HttpStatusCode.BadRequest)
                    val deleteResponse = notesService.deleteNote(noteId)
                    call.sendResponse(deleteResponse)

                }

                post {
                    val userId = call.userId() ?: ""
                    val editNoteRequest = call.receive<EditNotesRequest>()
                    val editResponse = notesService.editNote(editNoteRequest, userId)
                    call.sendResponse(editResponse)
                }


                get {
                    val userId = call.userId() ?: return@get call.respond(
                        HttpStatusCode.Unauthorized,
                        "Unauthorised"
                    )
                    val notesResponse = notesService.getNotesPerUserId(userId)
                    call.sendResponse(notesResponse)
                }
            }
        }
    }

}






