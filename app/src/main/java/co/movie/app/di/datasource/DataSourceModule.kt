package co.movie.app.di.datasource

import co.movie.app.datasourse.MoviesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideNewsDataSource(retrofit: Retrofit): MoviesDataSource =
            retrofit.create(MoviesDataSource::class.java)
}