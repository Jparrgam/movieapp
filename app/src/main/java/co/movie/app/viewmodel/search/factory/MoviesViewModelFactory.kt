package co.movie.app.viewmodel.search.factory

import co.movie.app.core.viewmodel.factory.AssistedViewModelFactory
import co.movie.app.viewmodel.search.MoviesViewModel
import co.movie.app.viewmodel.search.state.MoviesState
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MoviesViewModelFactory : AssistedViewModelFactory<MoviesViewModel, MoviesState> {
    override fun create(state: MoviesState): MoviesViewModel
}