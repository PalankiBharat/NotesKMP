package com.bharat.noteskmp.security.token

interface TokenInterface {
    fun generate(
        config: TokenConfig,
        vararg claims: TokenClaim
    ):String
}