package com.bharat.noteskmp.route

import com.bharat.noteskmp.data.repository.NotesRepositoryImpl
import com.bharat.noteskmp.service.notes.NotesService
import com.bharat.noteskmp.service.notes.NotesServiceImpl
import data.requests.AddNotesRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.notesRoute() {
    val notesService: NotesService = NotesServiceImpl(NotesRepositoryImpl())

    route(RouteConstants.API_VERSION) {
        route(RouteConstants.Notes.ROUTE) {

            post {
                val notesParam = call.receive<AddNotesRequest>()
                val response = notesService.addNote(notesParam)
                call.respond(response.first, response.second)
            }

            get("/{user_id}") {
                val userId = call.parameters["user_id"].toString()
                val notesResponse = notesService.getNotesPerUserId(userId)
                call.respond(notesResponse.first, notesResponse.second)
            }


        }
    }

}