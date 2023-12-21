package com.bharat.noteskmp.plugins


import com.bharat.noteskmp.route.notesRoute
import com.bharat.noteskmp.service.notes.NotesService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        notesRoute()
    }
    routing {
        route("/") {
            get {
                call.respond("Hi my name is bharat and this is my kmp project")
            }
        }
    }
}