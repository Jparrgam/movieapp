package co.movie.app.viewmodel

import co.movie.app.MainCoroutinesRule
import co.movie.app.core.either.Either
import co.movie.app.core.exception.Failure
import co.movie.app.model.MoviesResults
import co.movie.app.usecase.GetLocalMoviesUseCase
import co.movie.app.usecase.GetMoviesUseCase
import co.movie.app.viewmodel.search.MoviesViewModel
import co.movie.app.viewmodel.search.state.MoviesState
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val mvrxRule = MvRxTestRule()

    private val getMoviesUseCase: GetMoviesUseCase = mock()
    private val localMoviesUseCase: GetLocalMoviesUseCase = mock()

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        viewModel = MoviesViewModel(
                initialState = MoviesState(),
                moviesUseCase = getMoviesUseCase,
                localMoviesUseCase = localMoviesUseCase
        )
    }

    @Test
    fun searchMovies_ChangeStatusSuccess(): Unit = runBlocking {
        val captor = argumentCaptor<(Flow<Either<Failure, MoviesResults>>) -> Unit>()

        verify(getMoviesUseCase).run(params = 1)

        withState(viewModel) {
            assertEquals(it.isLoading, true)
        }

        withState(viewModel) {
            assertTrue(it.moviesState is Success)
        }

        withState(viewModel) {
            assertEquals(it.isLoading, false)
        }
    }
}