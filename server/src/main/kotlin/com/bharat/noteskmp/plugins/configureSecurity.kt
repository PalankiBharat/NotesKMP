package com.bharat.noteskmp.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.bharat.noteskmp.security.hashing.SHA256HashingService
import com.bharat.noteskmp.security.token.JwtTokenService
import com.bharat.noteskmp.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    val secret = "cXRIJ57575KKFDJFcmcm"
    val issuer = "ktor.io"
    val algorithm = Algorithm.HMAC512(secret)

    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = issuer,
        secret = secret,
        audience = "users",
        expiresIn = 365L * 1000L * 60L * 60L * 24L)
    val hashingService = SHA256HashingService()

    val jwtAudience = "jwt-audience"
    val jwtRealm = "ktor sample app"
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(tokenConfig.secret))
                    .withAudience(tokenConfig.audience)
                    .withIssuer(tokenConfig.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}