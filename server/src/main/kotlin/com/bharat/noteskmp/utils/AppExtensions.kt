package com.bharat.noteskmp.utils

import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.route.RouteConstants.User.USER_ID
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond

fun ApplicationCall.userId(): String? =
    this.principal<JWTPrincipal>()?.getClaim(USER_ID, String::class)

suspend fun <T> ApplicationCall.sendResponse(pair: Pair<HttpStatusCode, BasicResponseModel<T>>) {
    this.respond(pair.first, pair.second)
}
