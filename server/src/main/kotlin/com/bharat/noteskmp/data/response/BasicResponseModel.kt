package com.bharat.noteskmp.data.response

import kotlinx.serialization.Serializable

@Serializable
data class BasicResponseModel<T>(
    val status:String,
    val data:T?= null,
    val message:String
)

fun <T>failureResponse(errorMessage: String): BasicResponseModel<T> {
    return   BasicResponseModel(
        status = "Failure",
        message = errorMessage
    )
}

fun <T>successResponse(message: String,data: T?): BasicResponseModel<T> {
    return   BasicResponseModel(
        status = "Success",
        data = data,
        message = message
    )
}