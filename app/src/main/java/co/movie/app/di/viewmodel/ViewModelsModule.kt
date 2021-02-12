package co.movie.app.di.viewmodel

import co.movie.app.core.viewmodel.ViewModelKey
import co.movie.app.core.viewmodel.factory.AssistedViewModelFactory
import co.movie.app.di.viewmodel.config.MavericksViewModelComponent
import co.movie.app.viewmodel.detail.DetailMovieViewModel
import co.movie.app.viewmodel.detail.factory.MovieDetailViewModelFactory
import co.movie.app.viewmodel.search.MoviesViewModel
import co.movie.app.viewmodel.search.factory.MoviesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    fun moviesViewModelFactory(factory: MoviesViewModelFactory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    fun detailMoviesViewModelFactory(factory: MovieDetailViewModelFactory): AssistedViewModelFactory<*, *>
}