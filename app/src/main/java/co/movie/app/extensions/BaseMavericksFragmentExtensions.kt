package co.movie.app.extensions

import co.movie.app.core.base.BaseMavericksEpoxyFragment
import co.movie.app.core.base.MavericksEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.withState

fun <S : MavericksState, A : MavericksViewModel<S>> BaseMavericksEpoxyFragment.simpleController(
        viewModel: A,
        buildModels: EpoxyController.(state: S) -> Unit
) = MavericksEpoxyController {
    if (view == null || isRemoving) return@MavericksEpoxyController
    withState(viewModel) { state ->
        buildModels(state)
    }
}