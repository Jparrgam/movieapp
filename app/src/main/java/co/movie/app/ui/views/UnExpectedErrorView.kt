package co.movie.app.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import co.movie.app.databinding.UnExpectedErrorViewBinding
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class UnExpectedErrorView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var binding = UnExpectedErrorViewBinding.inflate(
            LayoutInflater.from(context), this, true)

    var clickListener: OnClickListener? = null
        @CallbackProp set

    @ModelProp
    fun setVisibility(visible: Boolean) {
        this.isVisible = visible
    }

    @AfterPropsSet
    fun useValueProps() {
        binding.localSearch.setOnClickListener(clickListener)
    }
}