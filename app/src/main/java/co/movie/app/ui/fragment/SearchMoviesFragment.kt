package co.movie.app.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import co.movie.app.R
import co.movie.app.core.base.BaseMavericksEpoxyFragment
import co.movie.app.core.base.viewbinding.viewBinding
import co.movie.app.databinding.SearchMoviesFragmentBinding
import co.movie.app.extensions.simpleController
import co.movie.app.ui.views.headerTitleView
import co.movie.app.ui.views.itemMovieView
import co.movie.app.ui.views.loadingWidget
import co.movie.app.ui.views.unExpectedErrorView
import co.movie.app.viewmodel.detail.args.DetailMoviesArgs
import co.movie.app.viewmodel.search.MoviesViewModel
import com.airbnb.mvrx.fragmentViewModel

class SearchMoviesFragment: BaseMavericksEpoxyFragment(R.layout.search_movies_fragment) {

    private val viewModel: MoviesViewModel by fragmentViewModel()
    private val binding: SearchMoviesFragmentBinding by viewBinding()

    override fun initUi() {
        configureList()
    }

    override fun invalidate() {
        binding.rcvSearchMovies.requestModelBuild()
    }

    override fun epoxyController() = simpleController(viewModel) { state ->
        headerTitleView {
            id("headerTitle")
        }

        state.movies.forEach { movie ->
            itemMovieView {
                id(movie.id)
                image(movie.posterPath)
                title(movie.title)
                overView(movie.overview)
                clickListener { _ ->
                    navigateTo(
                        R.id.action_searchMoviesFragment_to_detailMovieFragment,
                        DetailMoviesArgs(movieId = movie.id)
                    )
                }
            }
        }

        loadingWidget {
            visibility(state.isLoading)
            id("loading${state.movies.size}")
            onBind { _, _, _ -> viewModel.search() }
        }

        unExpectedErrorView {
            id("errorView")
            visibility(state.failedState)
            clickListener { _ ->
                viewModel.localSearch()
            }
        }
    }

    private fun configureList() {
        binding.rcvSearchMovies.setController(epoxyController)
        binding.rcvSearchMovies.setHasFixedSize(true)
        binding.rcvSearchMovies.layoutManager = LinearLayoutManager(context)
    }
}