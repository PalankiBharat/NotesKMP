package com.bharat.noteskmp.service.auth

import Note
import com.bharat.noteskmp.data.response.BasicResponseModel
import data.requests.SignupRequest
import io.ktor.http.*

interface AuthService {
    suspend fun signup(note: SignupRequest): Pair<HttpStatusCode, BasicResponseModel<Nothing>>

    suspend fun login(userId: String): Pair<HttpStatusCode, BasicResponseModel<List<Note>>>
}