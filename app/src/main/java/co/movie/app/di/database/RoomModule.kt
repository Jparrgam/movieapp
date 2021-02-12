package co.movie.app.di.database

import android.content.Context
import androidx.room.Room
import co.movie.app.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providerMoviesDatase(@ApplicationContext context: Context): MoviesDatabase =
        Room.databaseBuilder(context, MoviesDatabase::class.java, "movies_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesMoviesDao(dataBase: MoviesDatabase) = dataBase.moviesDao()
}