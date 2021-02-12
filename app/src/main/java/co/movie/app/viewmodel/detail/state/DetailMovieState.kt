package co.movie.app.viewmodel.detail.state

import co.movie.app.model.MovieResultItemDto
import co.movie.app.viewmodel.detail.args.DetailMoviesArgs
import com.airbnb.mvrx.*

data class DetailMovieState(
    val movieDetail: Async<MovieResultItemDto> = Uninitialized,
    val movieId: Int = 0
): MavericksState {
    constructor(args: DetailMoviesArgs) : this(movieId = args.movieId)

    val isCompleteState = movieDetail is Success

    val isLoading = movieDetail is Loading
}