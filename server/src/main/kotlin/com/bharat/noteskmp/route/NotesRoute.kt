package com.bharat.noteskmp.route

import com.bharat.noteskmp.route.RouteConstants.User.USER_ID
import com.bharat.noteskmp.route.RouteConstants.User.USER_ID_PATH
import com.bharat.noteskmp.route.authenticate
import com.bharat.noteskmp.service.notes.NotesService
import com.bharat.noteskmp.utils.userId
import data.requests.AddNotesRequest
import io.ktor.client.engine.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
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
                    call.respond(response.first, response.second)
                }

                get {
                    val userId = call.userId() ?: return@get call.respond(HttpStatusCode.Unauthorized, "Unauthorised")
                    val notesResponse = notesService.getNotesPerUserId(userId)
                    call.respond(notesResponse.first, notesResponse.second)
                }
            }
        }
    }

}


