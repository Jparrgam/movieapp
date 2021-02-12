package co.movie.app.datasourse

import co.movie.app.core.http.response.ApiResponse
import co.movie.app.model.MoviesResults
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDataSource {
    @GET("discover/movie") fun getMoviesAsync(
            @Query("include_video") includeVideo: Boolean? = true,
            @Query("page") page: Int = 1
    ): Deferred<ApiResponse<MoviesResults>>
}