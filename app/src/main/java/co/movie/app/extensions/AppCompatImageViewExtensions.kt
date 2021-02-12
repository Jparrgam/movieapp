package co.movie.app.extensions

import androidx.appcompat.widget.AppCompatImageView
import co.movie.app.R
import coil.load
import coil.request.CachePolicy
import coil.transform.RoundedCornersTransformation

fun AppCompatImageView.loadUrl(url: String? = "", roundedCorners: Float = 10f) {
    load(url) {
        memoryCachePolicy(CachePolicy.ENABLED)
        placeholder(R.drawable.placeholder)
        crossfade(true)
        error(R.drawable.placeholder)
        transformations(RoundedCornersTransformation(roundedCorners))
    }
}