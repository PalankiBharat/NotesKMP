package data.respository.auth

import data.requests.SignupRequest
import data.respository.auth.AuthRepository
import org.koin.core.component.KoinComponent

class AuthRepositoryImpl: AuthRepository, KoinComponent {
    override suspend fun signUpUser(signupRequest: SignupRequest): Boolean {
        return true
    }

    override suspend fun loginUser(userId: String) {

    }
}