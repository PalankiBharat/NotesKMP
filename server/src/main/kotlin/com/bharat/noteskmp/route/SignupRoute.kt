package com.bharat.noteskmp.route

import com.bharat.noteskmp.data.repository.AuthRepositoryImpl
import com.bharat.noteskmp.route.RouteConstants.API_VERSION
import com.bharat.noteskmp.route.RouteConstants.User.Auth.SIGN_UP_ROUTE
import com.bharat.noteskmp.service.auth.AuthService
import com.bharat.noteskmp.service.auth.AuthServiceImpl
import data.requests.SignupRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signUp()
{
    val notesService: AuthService = AuthServiceImpl(AuthRepositoryImpl())

    route(API_VERSION){
        post (SIGN_UP_ROUTE){
            val request = call.receive<SignupRequest>()
            val response = notesService.signup(request)
            call.respond(response.first,response.second)
        }
    }

}