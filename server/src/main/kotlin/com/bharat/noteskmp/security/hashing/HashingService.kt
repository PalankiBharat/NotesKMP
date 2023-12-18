package com.bharat.noteskmp.security.hashing

interface HashingService {
    fun generateSaltedHash(value:String, saltLength:Int):SaltHash
    fun verify(value:String, saltedHash: SaltHash):Boolean
}