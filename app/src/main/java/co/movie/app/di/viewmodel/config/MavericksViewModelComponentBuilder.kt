package co.movie.app.di.viewmodel.config

import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface MavericksViewModelComponentBuilder {
    fun build(): MavericksViewModelComponent
}
