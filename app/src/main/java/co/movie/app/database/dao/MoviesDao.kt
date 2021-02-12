package co.movie.app.database.dao

import androidx.room.*
import co.movie.app.model.MovieResultItemDto

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(news: List<MovieResultItemDto>)

    @get:Query("SELECT * FROM movie")
    val movies: List<MovieResultItemDto>

    @Query("SELECT * FROM movie where id = :movieId")
    fun detailMovie(movieId: Int): MovieResultItemDto
}