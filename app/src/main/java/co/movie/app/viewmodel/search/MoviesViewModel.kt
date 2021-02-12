package co.movie.app.viewmodel.search

import co.movie.app.core.either.Either
import co.movie.app.core.usecase.UseCase
import co.movie.app.di.viewmodel.config.hiltMavericksViewModelFactory
import co.movie.app.usecase.GetLocalMoviesUseCase
import co.movie.app.usecase.GetMoviesUseCase
import co.movie.app.viewmodel.search.state.MoviesState
import com.airbnb.mvrx.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class MoviesViewModel @AssistedInject constructor(
    @Assisted initialState: MoviesState,
    private val moviesUseCase: GetMoviesUseCase,
    private val localMoviesUseCase: GetLocalMoviesUseCase
) : MavericksViewModel<MoviesState>(initialState) {

    private var page: Int = 1
    private var totalPage = 1

    init { search() }

    fun search() = withState { state ->
        if (state.movies.size < totalPage) {
            moviesUseCase(params = page) { request ->
                request.execute { response ->
                    val moviesResult = if(response is Success) {
                        when(val result = response.invoke()) {
                            is Either.Right -> {
                                page += 1
                                totalPage = result.success.totalPages
                                copy(
                                        moviesState = Success(result.success),
                                        movies = movies + result.success.results,
                                        failedState = false
                                )
                            }

                            is Either.Fail -> copy(
                                    failedState = true
                            )
                        }
                    } else {
                        copy(
                                moviesState = Loading(),
                                movies = movies + emptyList(),
                        )
                    }

                    moviesResult
                }
            }
        }
    }

    fun localSearch() = withState {
        localMoviesUseCase(UseCase.None()) { localRequest ->
            localRequest.execute { localResponse ->
                return@execute when(val result = localResponse.invoke()) {
                    is Either.Right -> {
                        copy(
                                moviesState = Success(result.success),
                                movies = movies + result.success.results,
                                failedState = false
                        )
                    }
                    else -> copy(
                            failedState = true
                    )
                }
            }
        }
    }

    companion object : MavericksViewModelFactory<MoviesViewModel, MoviesState> by hiltMavericksViewModelFactory()
}