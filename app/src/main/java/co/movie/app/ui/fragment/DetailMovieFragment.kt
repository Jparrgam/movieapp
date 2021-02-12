package co.movie.app.ui.fragment

import androidx.core.view.isVisible
import co.movie.app.R
import co.movie.app.core.base.BaseMavericksFragment
import co.movie.app.core.base.viewbinding.viewBinding
import co.movie.app.databinding.DetailMovieFragmentBinding
import co.movie.app.extensions.loadUrl
import co.movie.app.model.MovieResultItemDto.Companion.BASE_BACKDROP_PATH
import co.movie.app.viewmodel.detail.DetailMovieViewModel
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState

class DetailMovieFragment : BaseMavericksFragment(R.layout.detail_movie_fragment) {

    private val viewBinding: DetailMovieFragmentBinding by viewBinding()

    private val detailNewsViewModel: DetailMovieViewModel by fragmentViewModel()

    override fun initUi() {
        detailNewsViewModel.detailMovieWithId()
    }

    override fun invalidate() = withState(detailNewsViewModel) { state ->
        if (state.isCompleteState) {
            state.movieDetail.invoke()?.let {
                viewBinding.movieTitle.text = it.title
                viewBinding.movieOverview.text = it.overview
                viewBinding.movieBackdropImage.loadUrl("$BASE_BACKDROP_PATH${it.backdropPath}", 0f)
            }
        }

        viewBinding.loadingMovieDetail.isVisible = state.isLoading
    }
}