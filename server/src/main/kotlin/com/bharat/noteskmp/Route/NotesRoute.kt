package com.bharat.noteskmp.Route

import Note
import com.bharat.noteskmp.data.repository.NotesRepository
import com.bharat.noteskmp.data.repository.NotesRepositoryImpl
import com.bharat.noteskmp.data.request.AddNotesRequest
import com.bharat.noteskmp.data.response.failureResponse
import com.bharat.noteskmp.service.NotesService
import com.bharat.noteskmp.service.NotesServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.notesRoute() {
    val notesService: NotesService = NotesServiceImpl(NotesRepositoryImpl())

    route(RouteSegment.API_VERSION) {
        route(RouteSegment.Notes.ROUTE) {

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