package co.movie.app.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.Nullable
import androidx.cardview.widget.CardView
import co.movie.app.databinding.ItemMovieRowBinding
import co.movie.app.extensions.loadUrl
import co.movie.app.model.MovieResultItemDto.Companion.BASE_POSTER_PATH
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ItemMovieView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var binding = ItemMovieRowBinding.inflate(
            LayoutInflater.from(context), this, true)

    var clickListener: OnClickListener? = null
        @CallbackProp set

    @ModelProp
    lateinit var title: String

    @ModelProp
    fun setOverView(@Nullable overview: String?) {
        binding.movieOverview.text = overview
    }

    @ModelProp
    fun setImage(@Nullable image: String?) {
        binding.movieImage.loadUrl("${BASE_POSTER_PATH}$image")
    }

    @AfterPropsSet
    fun useValueProps() {
        binding.movieTitle.text = title
        binding.containerItemMovie.setOnClickListener(clickListener)
    }
}