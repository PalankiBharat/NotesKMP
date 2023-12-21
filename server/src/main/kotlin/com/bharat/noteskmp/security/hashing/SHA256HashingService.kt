package com.bharat.noteskmp.security.hashing

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import org.koin.core.component.KoinComponent
import java.security.SecureRandom

class SHA256HashingService:HashingService, KoinComponent {
    override fun generateSaltedHash(value: String, saltLength: Int): SaltHash {
        val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLength)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$salt$value")
        return SaltHash(hash = hash, salt = saltAsHex)
    }

    override fun verify(value: String, saltedHash: SaltHash): Boolean {
        return DigestUtils.sha256Hex(saltedHash.salt+value) == saltedHash.hash
    }
}