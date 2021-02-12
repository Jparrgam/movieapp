package co.movie.app.di.viewmodel.config

import co.movie.app.core.viewmodel.factory.AssistedViewModelFactory
import com.airbnb.mvrx.MavericksViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(MavericksViewModelComponent::class)
interface HiltMavericksEntryPoint {
    val viewModelFactories: Map<Class<out MavericksViewModel<*>>, AssistedViewModelFactory<*, *>>
}