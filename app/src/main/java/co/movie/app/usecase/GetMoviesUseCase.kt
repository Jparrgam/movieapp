package co.movie.app.usecase

import co.movie.app.core.usecase.UseCase
import co.movie.app.model.MoviesResults
import co.movie.app.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
        private val moviesRepository: MoviesRepository
): UseCase<MoviesResults, Int>() {
    override suspend fun run(params: Int) =
            moviesRepository.search(page = params)
}