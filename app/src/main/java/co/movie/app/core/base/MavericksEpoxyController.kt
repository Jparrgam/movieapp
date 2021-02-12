package co.movie.app.core.base

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController

open class MavericksEpoxyController(
        val buildModelsCallback: EpoxyController.() -> Unit = {}
) : AsyncEpoxyController() {
    override fun buildModels() {
        buildModelsCallback()
    }
}