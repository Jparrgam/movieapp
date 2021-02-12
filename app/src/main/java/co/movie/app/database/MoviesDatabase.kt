package co.movie.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.movie.app.database.dao.MoviesDao
import co.movie.app.model.MovieResultItemDto

@Database(entities = [MovieResultItemDto::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}