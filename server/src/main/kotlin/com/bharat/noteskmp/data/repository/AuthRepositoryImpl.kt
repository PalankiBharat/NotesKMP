package com.bharat.noteskmp.data.repository

import com.bharat.noteskmp.data.model.UserEntity
import com.bharat.noteskmp.security.hashing.HashingService
import com.mongodb.kotlin.client.coroutine.MongoCollection
import data.requests.SignupRequest
import data.respository.auth.AuthRepository
import org.bson.types.ObjectId

class AuthRepositoryImpl(
    private val userCollection: MongoCollection<UserEntity>,
    private val hashingService: HashingService,
) : AuthRepository {
    override suspend fun signUpUser(signupRequest: SignupRequest): Boolean {
        val saltedHash = hashingService.generateSaltedHash(signupRequest.password, 16)
        val user = UserEntity(
            id = ObjectId().toHexString(),
            email = signupRequest.email,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        return userCollection.insertOne(user).wasAcknowledged()
    }

    override suspend fun loginUser(userId: String) {
        TODO("Not yet implemented")
    }
}