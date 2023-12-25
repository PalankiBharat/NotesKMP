package com.bharat.noteskmp.plugins


import com.bharat.noteskmp.route.authRoutes
import com.bharat.noteskmp.route.authenticate
import com.bharat.noteskmp.route.notesRoute
import com.bharat.noteskmp.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(tokenConfig: TokenConfig) {
    routing {
        authenticate()
        authRoutes(tokenConfig)
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