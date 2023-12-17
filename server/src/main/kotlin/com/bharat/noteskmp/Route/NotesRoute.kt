package com.bharat.noteskmp.Route

import Note
import com.bharat.noteskmp.data.repository.NotesRepository
import com.bharat.noteskmp.data.repository.NotesRepositoryImpl
import com.bharat.noteskmp.data.request.AddNotesRequest
import com.bharat.noteskmp.data.response.failureResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.notesRoute(){
    val notesRepository:NotesRepository = NotesRepositoryImpl()

    route(RouteSegment.API_VERSION){
        route(RouteSegment.Notes.ROUTE){
            post {
                val notesParam = call.receive<AddNotesRequest>()
                val response = notesRepository.addNote(notesParam)
                call.respond(HttpStatusCode.Created, response)
            }

            get("/{user_id}") {
                val userId = call.parameters["user_id"].toString()
               val response =  if (userId.isEmpty()){
                   failureResponse("User Id Should not be blank")
               }else{
                   notesRepository.getNotesPerUserId(userId)
               }
                call.respond(HttpStatusCode.OK,response)
            }
        }


    }

}