package com.bharat.noteskmp

import SERVER_PORT
import com.bharat.noteskmp.di.configureDi
import com.bharat.noteskmp.plugins.configureContentNegotiation
import com.bharat.noteskmp.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDi()
    configureContentNegotiation()
    configureRouting()
}
