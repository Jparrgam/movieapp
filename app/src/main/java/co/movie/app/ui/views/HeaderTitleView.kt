package co.movie.app.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import co.movie.app.R
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HeaderTitleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr)  {

    init {
        inflate(context, R.layout.header_title_view, this)
        orientation = VERTICAL
    }
}