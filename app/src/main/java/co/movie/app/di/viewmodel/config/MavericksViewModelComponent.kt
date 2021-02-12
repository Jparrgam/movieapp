package co.movie.app.di.viewmodel.config

import co.movie.app.core.viewmodel.scope.MavericksViewModelScoped
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@MavericksViewModelScoped
@DefineComponent(parent = SingletonComponent::class)
interface MavericksViewModelComponent
