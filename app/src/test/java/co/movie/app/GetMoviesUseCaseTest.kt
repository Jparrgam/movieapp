package co.movie.app

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import app.cash.turbine.test
import co.movie.app.core.either.Either
import co.movie.app.core.exception.Failure
import co.movie.app.core.http.response.Success
import co.movie.app.model.MovieResultItemDto
import co.movie.app.model.MoviesResults
import co.movie.app.repository.MoviesRepository
import co.movie.app.usecase.GetMoviesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    private val repository: MoviesRepository = mock()

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun setup() {
        getMoviesUseCase = GetMoviesUseCase(moviesRepository = repository)
    }

    @ExperimentalTime
    @Test
    fun fetchMovies_WithIncorrectPage_ThrowExceptionTest(): Unit =  runBlocking {
        whenever(repository.search(page = 0)).thenReturn(
            flow {
                emit(Either.Fail(Failure.ServerError(message = "page must be greater than 0")))
            }
        )

        getMoviesUseCase.run(params = 0).test {
            assertEquals(expectError().message, "page must be greater than 0")
            assertNull(expectError().message)
            expectComplete()
        }
    }

    @ExperimentalTime
    @Test
    fun fetchMovies_WithCorrectParams_ApiResponseOkTest(): Unit = runBlocking {
        val mockData = MoviesResults(page = 1, totalPages = 20)

        whenever(repository.search(page = 1)).thenReturn(
            flow {
                emit(Either.Right(
                   success = mockData)
                )
            }
        )

        getMoviesUseCase.run(params = 1).test {
            assertEquals(expectItem().isRight, true)
            assertEquals(expectItem().fnR.success, mockData)
            assertEquals(expectItem().fnR.success.page, 1)
            assertTrue(expectItem().fnR.success.totalPages > 0)
        }
    }

    @ExperimentalTime
    @Test
    fun fetchMovies_WithCorrectParamsNotMoviesInPage_ApiResponseOkNotEmptyList(): Unit = runBlocking {
        val mockData = MoviesResults(page = 100, totalPages = 100,
            results = arrayListOf(
                MovieResultItemDto(
                   title = "Movie testing",
                    id = 3211
                )
            ))

        whenever(repository.search(page = 100)).thenReturn(
            flow {
                emit(Either.Right(
                    success = mockData)
                )
            }
        )

        getMoviesUseCase.run(params = 100).test {
            assertNotNull(expectItem().fnR.success.results)
        }
    }
}