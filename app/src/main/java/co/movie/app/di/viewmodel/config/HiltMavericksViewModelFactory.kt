@file:Suppress("UNCHECKED_CAST")

package co.movie.app.di.viewmodel.config

import co.movie.app.core.viewmodel.factory.AssistedViewModelFactory
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import dagger.hilt.EntryPoints

class HiltMavericksViewModelFactory<VM : MavericksViewModel<S>, S : MavericksState>(
        private val viewModelClass: Class<out MavericksViewModel<S>>
) : MavericksViewModelFactory<VM, S> {

    override fun create(viewModelContext: ViewModelContext, state: S): VM {
        val componentBuilder = EntryPoints.get(viewModelContext.app(), CreateMavericksViewModelComponent::class.java).mavericksViewModelComponentBuilder()
        val viewModelComponent = componentBuilder.build()
        val viewModelFactoryMap = EntryPoints.get(viewModelComponent, HiltMavericksEntryPoint::class.java).viewModelFactories
        val viewModelFactory = viewModelFactoryMap[viewModelClass]

        val castedViewModelFactory = viewModelFactory as? AssistedViewModelFactory<VM, S>
        return castedViewModelFactory?.create(state) as VM
    }
}

inline fun <reified VM : MavericksViewModel<S>, S : MavericksState> hiltMavericksViewModelFactory() =
        HiltMavericksViewModelFactory<VM, S>(VM::class.java)