package co.movie.app.viewmodel.detail

import co.movie.app.core.either.Either
import co.movie.app.di.viewmodel.config.hiltMavericksViewModelFactory
import co.movie.app.usecase.GetDetailMovieUseCase
import co.movie.app.viewmodel.detail.state.DetailMovieState
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class DetailMovieViewModel @AssistedInject constructor(
    @Assisted state: DetailMovieState,
    private val detailMovieUseCase: GetDetailMovieUseCase
) : MavericksViewModel<DetailMovieState>(state) {

    fun detailMovieWithId() = withState { state ->
        detailMovieUseCase.invoke(params = state.movieId) { request ->
            request.execute { response ->
                val detailResult = if (response is Success) {
                    when (val result = response.invoke()) {
                        is Either.Right -> copy(
                            movieDetail = Success(
                                value = result.success
                            )
                        )
                        is Either.Fail -> copy()
                    }
                } else {
                    copy(
                        movieDetail = Loading(),
                    )
                }

                detailResult
            }
        }
    }

    companion object :
        MavericksViewModelFactory<DetailMovieViewModel, DetailMovieState> by hiltMavericksViewModelFactory()
}