package data.respository

import data.requests.SignupRequest

interface AuthRepository {
    suspend fun signUpUser(signupRequest: SignupRequest):Boolean

    suspend fun loginUser(userId:String)
}