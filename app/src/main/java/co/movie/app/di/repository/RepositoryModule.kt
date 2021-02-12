package co.movie.app.di.repository

import co.movie.app.repository.MoviesRepository
import co.movie.app.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository =
        moviesRepositoryImpl
}