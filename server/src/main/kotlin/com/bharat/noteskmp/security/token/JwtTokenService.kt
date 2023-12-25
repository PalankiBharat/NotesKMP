package com.bharat.noteskmp.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JwtTokenService: TokenService {
    private  val secret = "cXRIJ57575KKFDJFcmcm"
    private  val issuer = "ktor.io"
    private val algorithm = Algorithm.HMAC512(secret)

    override fun generate(config: TokenConfig, vararg claims: TokenClaim): String {
        var token =  JWT.create()
            .withAudience(config.audience)
            .withIssuer(config.issuer)
            .withExpiresAt(Date(System.currentTimeMillis()+config.expiresIn))
        claims.forEach {
            token = token.withClaim(it.name, it.value)
        }
        return token.sign(Algorithm.HMAC256(config.secret))
    }

}