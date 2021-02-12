package co.movie.app.viewmodel.detail.factory

import co.movie.app.core.viewmodel.factory.AssistedViewModelFactory
import co.movie.app.viewmodel.detail.DetailMovieViewModel
import co.movie.app.viewmodel.detail.state.DetailMovieState
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MovieDetailViewModelFactory : AssistedViewModelFactory<DetailMovieViewModel, DetailMovieState> {
    override fun create(state: DetailMovieState): DetailMovieViewModel
}