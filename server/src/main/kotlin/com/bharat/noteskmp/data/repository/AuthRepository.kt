package com.bharat.noteskmp.data.repository

import com.bharat.noteskmp.data.model.UserEntity
import data.requests.LoginRequest
import data.requests.SignupRequest

interface AuthRepository {
    suspend fun signUpUser(signupRequest: SignupRequest):Boolean

    suspend fun loginUser(loginRequest:LoginRequest):Boolean

    suspend fun findUserOrNull(email:String):UserEntity?
}