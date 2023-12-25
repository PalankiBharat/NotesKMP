package com.bharat.noteskmp.route

import com.bharat.noteskmp.route.RouteConstants.User.USER_ID
import com.bharat.noteskmp.route.RouteConstants.User.USER_ID_PATH
import com.bharat.noteskmp.service.notes.NotesService
import data.requests.AddNotesRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.notesRoute() {
    val notesService: NotesService by inject()
    route(RouteConstants.API_VERSION) {
        route(RouteConstants.Notes.ROUTE) {
            post {
                val notesParam = call.receive<AddNotesRequest>()
                val response = notesService.addNote(notesParam)
                call.respond(response.first, response.second)
            }

            get("/$USER_ID_PATH") {
                val userId = call.parameters[USER_ID].toString()
                val notesResponse = notesService.getNotesPerUserId(userId)
                call.respond(notesResponse.first, notesResponse.second)
            }


        }
    }

}