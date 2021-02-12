package co.movie.app.repository

import androidx.annotation.WorkerThread
import co.movie.app.core.either.Either
import co.movie.app.core.exception.Failure
import co.movie.app.model.MovieResultItemDto
import co.movie.app.model.MoviesResults
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    @WorkerThread
    fun search(page: Int = 1): Flow<Either<Failure, MoviesResults>>

    @WorkerThread
    fun detail(movieId: Int): Flow<Either<Failure, MovieResultItemDto>>

    @WorkerThread
    fun localSearch(): Flow<Either<Failure, MoviesResults>>
}