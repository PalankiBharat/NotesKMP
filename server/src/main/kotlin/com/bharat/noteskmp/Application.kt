package com.bharat.noteskmp

import SERVER_PORT
import com.bharat.noteskmp.plugins.configureContentNegotiation
import com.bharat.noteskmp.plugins.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureContentNegotiation()
    configureRouting()
}
