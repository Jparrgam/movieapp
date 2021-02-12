package co.movie.app.usecase

import co.movie.app.core.either.Either
import co.movie.app.core.exception.Failure
import co.movie.app.core.usecase.UseCase
import co.movie.app.model.MovieResultItemDto
import co.movie.app.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
        private val moviesRepository: MoviesRepository
): UseCase<MovieResultItemDto, Int>() {
    override suspend fun run(params: Int): Flow<Either<Failure, MovieResultItemDto>> =
            moviesRepository.detail(movieId = params)
}