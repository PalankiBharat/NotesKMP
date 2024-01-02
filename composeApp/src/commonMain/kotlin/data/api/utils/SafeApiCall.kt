package data.api.utils

import data.models.BasicResponseModel
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> BasicResponseModel<T>,
): ApiResult<T?> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()
            if (response.status == "Success" && response.data != null) {
                if (response.data != null) {
                    ApiResult.Success(data = response.data)
                } else {
                    ApiResult.GenericError(errorMessage = "No Data Found")
                }
            } else {
                ApiResult.GenericError(errorMessage = response.message)
            }
            ApiResult.Success(response.data)
        } catch (throwable: Throwable) {
            // printLogD(TAG, "Exception: $throwable")
            when (throwable) {
                is ClientRequestException -> convertErrorBody(throwable)
                is ServerResponseException -> convertErrorBody(throwable)
                is TimeoutCancellationException -> ApiResult.GenericError(errorMessage = AppErrorCodes.NETWORK_ERROR_TIMEOUT)
                is IOException -> ApiResult.NetworkError
                else -> ApiResult.GenericError(errorMessage = AppErrorCodes.SOMETHING_WENT_WRONG)
            }
        }
    }
}

private suspend fun convertErrorBody(throwable: ResponseException): ApiResult.GenericError {
    return try {
        val errorResponse = throwable.response.body<BasicResponseModel<Nothing>>()
        ApiResult.GenericError(errorResponse.message)
    } catch (exception: Exception) {
        ApiResult.GenericError(errorMessage = AppErrorCodes.SOMETHING_WENT_WRONG)
    }
}