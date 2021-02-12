package co.movie.app.core.http.response

import retrofit2.Response

sealed class ApiResponse<out T> {
    companion object {
        fun create(error: Throwable): NetworkOrUnknownError = NetworkOrUnknownError(error)

        fun <T> create(response: Response<T>): ApiResponse<T> =
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    Success(body = null)
                } else {
                    Success(body = body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                when (response.code()) {
                    404, 503 -> ApiError(message = errorMsg)
                    else -> NetworkOrUnknownError(error = null)
                }
            }
    }
}

data class Success<T : Any>(val body: T?) : ApiResponse<T>()

data class ApiError(val message: String?) : ApiResponse<Nothing>()

data class NetworkOrUnknownError(val error: Throwable?) : ApiResponse<Nothing>()