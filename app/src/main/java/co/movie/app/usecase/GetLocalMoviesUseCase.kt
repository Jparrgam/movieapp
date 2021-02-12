package co.movie.app.usecase

import co.movie.app.core.either.Either
import co.movie.app.core.exception.Failure
import co.movie.app.core.usecase.UseCase
import co.movie.app.model.MoviesResults
import co.movie.app.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalMoviesUseCase @Inject constructor(
        private val moviesRepository: MoviesRepository
): UseCase<MoviesResults, UseCase.None>() {
    override suspend fun run(params: None): Flow<Either<Failure, MoviesResults>> =
            moviesRepository.localSearch()
}