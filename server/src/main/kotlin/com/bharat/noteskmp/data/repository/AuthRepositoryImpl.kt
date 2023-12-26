package com.bharat.noteskmp.data.repository

import com.bharat.noteskmp.data.model.UserEntity
import com.bharat.noteskmp.route.RouteConstants
import com.bharat.noteskmp.security.hashing.HashingService
import com.bharat.noteskmp.security.hashing.SaltHash
import com.mongodb.client.model.Filters.*
import com.mongodb.kotlin.client.coroutine.MongoCollection
import data.requests.LoginRequest
import data.requests.SignupRequest
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class AuthRepositoryImpl(
    private val userCollection: MongoCollection<UserEntity>,
    private val hashingService: HashingService,
) : AuthRepository {
    override suspend fun signUpUser(signupRequest: SignupRequest): Boolean {
        userCollection
        val saltedHash = hashingService.generateSaltedHash(signupRequest.password, 16)
        val user = UserEntity(
            id = ObjectId().toHexString(),
            email = signupRequest.email,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        return userCollection.insertOne(user).wasAcknowledged()
    }

    override suspend fun loginUser(loginRequest: LoginRequest): Boolean {
        val user = findUserOrNull(loginRequest.email)
        var canUserLogin = false
        user?.let {
            val isPasswordValid = hashingService.verify(
                loginRequest.password, SaltHash(
                    hash = user.password,
                    salt = user.salt
                )
            )
            if (isPasswordValid) {
                canUserLogin = true
            }
        }
        return canUserLogin
    }

    override suspend fun findUserOrNull(email: String): UserEntity? {
        return userCollection.withDocumentClass<UserEntity>().find(eq("email", email)).toList().getOrNull(0)
    }
}