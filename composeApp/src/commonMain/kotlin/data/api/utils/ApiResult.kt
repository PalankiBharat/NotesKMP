package data.api.utils

sealed class ApiResult<out T> {

    data class Success<out T>(val data: T, val successMessage:String) : ApiResult<T>()

    data class GenericError(
        private val errorMessage: String? = null,
    ) : ApiResult<Nothing>()

    data object NetworkError : ApiResult<Nothing>()
}
