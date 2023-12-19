package com.bharat.noteskmp.data.repository

import com.bharat.noteskmp.data.model.UserEntity
import com.bharat.noteskmp.security.hashing.HashingService
import com.bharat.noteskmp.security.hashing.SHA256HashingService
import com.bharat.noteskmp.utils.StringConstants
import com.mongodb.kotlin.client.coroutine.MongoClient
import data.requests.SignupRequest
import data.respository.AuthRepository
import org.bson.types.ObjectId

class AuthRepositoryImpl:AuthRepository {
    val hashingService: HashingService = SHA256HashingService()
    private val uri = StringConstants.CONNECTION_STRING_URL
    private val mongoClient = MongoClient.create(uri)
    private val database = mongoClient.getDatabase(StringConstants.DATABASE_NAME)
    val userCollection = database.getCollection<UserEntity>(StringConstants.USER_COLLECTION_NAME)
    override suspend fun signUpUser(signupRequest: SignupRequest):Boolean {
        val saltedHash = hashingService.generateSaltedHash(signupRequest.password, 16)
        val user = UserEntity(
            id = ObjectId().toHexString(),
            email = signupRequest.email,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
      return  userCollection.insertOne(user).wasAcknowledged()
    }

    override suspend fun loginUser(userId: String) {
        TODO("Not yet implemented")
    }
}