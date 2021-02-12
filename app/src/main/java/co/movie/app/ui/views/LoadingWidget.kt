package co.movie.app.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isVisible
import co.movie.app.R
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class LoadingWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.loading_view, this)
    }

    @ModelProp
    fun setVisibility(visible: Boolean) {
        this.isVisible = visible
    }
}