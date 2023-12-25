package com.bharat.noteskmp.route

import com.bharat.noteskmp.security.token.TokenConfig
import com.bharat.noteskmp.service.auth.AuthService
import data.requests.LoginRequest
import data.requests.SignupRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.authenticate() {
    authenticate {
        get("authenticate") {
            call.respond(HttpStatusCode.OK)
        }
    }
}


fun Route.authRoutes(tokenConfig: TokenConfig) {
    val authService by inject<AuthService>()
    route(RouteConstants.API_VERSION) {
        post(RouteConstants.Auth.SIGN_UP_ROUTE) {
            val request = call.receive<SignupRequest>()
            val response = authService.signup(request)
            call.respond(response.first, response.second)
        }

        post(RouteConstants.Auth.LOGIN_ROUTE) {
            val request = call.receive<LoginRequest>()
            val response = authService.login(request, tokenConfig)
            call.respond(response.first, response.second)
        }
    }
}