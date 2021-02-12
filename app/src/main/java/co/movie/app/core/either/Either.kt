package co.movie.app.core.either

sealed class Either<out Failure, out Success> {
    val isRight
        get() =
            this is Right<Success>

    val isLeft
        get() =
            this is Fail<Failure>

    val fnL get() = this as Fail<Failure>

    val fnR get() = this as Right<Success>

    data class Fail<out Failure>(val fail: Failure?) : Either<Failure, Nothing>()

    data class Right<out Success>(val success: Success) : Either<Nothing, Success>()
}