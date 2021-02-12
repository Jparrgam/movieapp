package co.movie.app.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView

abstract class BaseMavericksFragment(@LayoutRes containerLayoutId: Int = 0): Fragment(containerLayoutId),
    MavericksView {

    abstract fun initUi()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }
}