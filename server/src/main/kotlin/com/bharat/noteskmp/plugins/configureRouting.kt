package com.bharat.noteskmp.plugins


import com.bharat.noteskmp.route.notesRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        notesRoute()
    }
    routing {
        route("/"){
            get {
                call.respond("Heloo ")
            }
        }
    }
}