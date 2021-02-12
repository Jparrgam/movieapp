package co.movie.app.di.viewmodel.config

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CreateMavericksViewModelComponent {
    fun mavericksViewModelComponentBuilder(): MavericksViewModelComponentBuilder
}