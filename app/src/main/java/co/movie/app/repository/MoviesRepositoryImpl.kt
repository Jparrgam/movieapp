package co.movie.app.repository

import co.movie.app.core.either.Either
import co.movie.app.core.exception.Failure
import co.movie.app.core.http.response.ApiError
import co.movie.app.core.http.response.NetworkOrUnknownError
import co.movie.app.core.http.response.Success
import co.movie.app.database.dao.MoviesDao
import co.movie.app.datasourse.MoviesDataSource
import co.movie.app.model.MovieResultItemDto
import co.movie.app.model.MoviesResults
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
        private val moviesDataSource: MoviesDataSource,
        private val coroutineDispatcher: CoroutineDispatcher,
        private val moviesDao: MoviesDao
): MoviesRepository {
    override fun search(page: Int): Flow<Either<Failure, MoviesResults>> = flow {
        when(val result = moviesDataSource.getMoviesAsync(page = page).await()) {
            is Success -> result.body?.let {
                moviesDao.insertNews(it.results)
                emit(Either.Right(success = it))
            }
            is ApiError -> emit(Either.Fail(Failure.ServerError(result.message)))
            is NetworkOrUnknownError -> emit(Either.Fail(Failure.ServerError()))
        }
    }.flowOn(coroutineDispatcher)

    override fun detail(movieId: Int): Flow<Either<Failure, MovieResultItemDto>> = flow {
        val movieDetail = moviesDao.detailMovie(movieId)
        emit(Either.Right(success = movieDetail))
    }.flowOn(coroutineDispatcher)

    override fun localSearch(): Flow<Either<Failure, MoviesResults>> = flow {
        val movies = moviesDao.movies
        if(movies.isNotEmpty()) {
            emit(Either.Right(success = MoviesResults(
                totalPages = movies.size,
                results = movies
            )))
        } else {
            emit(Either.Fail(Failure.ServerError()))
        }
    }.flowOn(coroutineDispatcher)
}