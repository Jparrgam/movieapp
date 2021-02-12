package co.movie.app.core.exception

sealed class Failure {
    data class ServerError(var message: String? = null) : Failure()
    object UnknownError: Failure()
}