package com.bharat.noteskmp.utils

import com.bharat.noteskmp.data.response.BasicResponseModel
import io.ktor.http.*

suspend fun <T> safeServerCall(serverCall: suspend () -> Pair<HttpStatusCode, BasicResponseModel<T>>): Pair<HttpStatusCode, BasicResponseModel<T>> {
    return try {
        serverCall()
    } catch (e: Exception) {
        internalServerErrorResult()
    }
}