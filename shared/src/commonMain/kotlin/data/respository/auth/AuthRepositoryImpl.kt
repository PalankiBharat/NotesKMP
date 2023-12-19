package data.respository.auth

import data.requests.SignupRequest
import data.respository.auth.AuthRepository

class AuthRepositoryImpl: AuthRepository {
    override suspend fun signUpUser(signupRequest: SignupRequest): Boolean {
        return true
    }

    override suspend fun loginUser(userId: String) {

    }
}