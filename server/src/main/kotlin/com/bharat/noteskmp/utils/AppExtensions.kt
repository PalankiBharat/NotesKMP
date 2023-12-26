package com.bharat.noteskmp.utils

import com.bharat.noteskmp.route.RouteConstants.User.USER_ID
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun ApplicationCall.userId(): String? = this.principal<JWTPrincipal>()?.getClaim(USER_ID, String::class)