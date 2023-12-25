package com.bharat.noteskmp

import SERVER_PORT
import com.bharat.noteskmp.di.configureDi
import com.bharat.noteskmp.plugins.configureContentNegotiation
import com.bharat.noteskmp.plugins.configureRouting
import com.bharat.noteskmp.plugins.configureSecurity
import com.bharat.noteskmp.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val secret = "cXRIJ57575KKFDJFcmcm"
    val issuer = "ktor.io"
    val tokenConfig = TokenConfig(
        issuer = issuer,
        secret = secret,
        audience = "users",
        expiresIn = 365L * 1000L * 60L * 60L * 24L)
    configureDi()
    configureContentNegotiation()
    configureSecurity(tokenConfig)
    configureRouting(tokenConfig)
}
