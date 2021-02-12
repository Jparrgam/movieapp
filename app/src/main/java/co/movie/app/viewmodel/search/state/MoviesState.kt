package co.movie.app.viewmodel.search.state

import co.movie.app.model.MovieResultItemDto
import co.movie.app.model.MoviesResults
import com.airbnb.mvrx.*

data class MoviesState(
        val moviesState: Async<MoviesResults> = Uninitialized,
        val movies: List<MovieResultItemDto> = mutableListOf(),
        val failedState: Boolean = false
): MavericksState {
    val isLoading = moviesState is Loading && !failedState
    val isComplete = moviesState is Success
}