package com.bharat.noteskmp.utils

import com.bharat.noteskmp.data.response.BasicResponseModel
import com.bharat.noteskmp.data.response.failureResponse
import io.ktor.http.*

fun <T>okResult(response:BasicResponseModel<T>):Pair<HttpStatusCode,BasicResponseModel<T>>{
    return Pair(HttpStatusCode.OK,response)
}

fun <T>internalServerErrorResult():Pair<HttpStatusCode,BasicResponseModel<T>>{
    return Pair(HttpStatusCode.InternalServerError, failureResponse(StringConstants.BASIC_ERROR_MESSAGE))
}

fun <T>commonResult(statusCode: HttpStatusCode,response:BasicResponseModel<T>):Pair<HttpStatusCode,BasicResponseModel<T>>{
    return Pair(statusCode,response)
}