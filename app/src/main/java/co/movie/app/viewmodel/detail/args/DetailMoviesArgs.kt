package co.movie.app.viewmodel.detail.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMoviesArgs(val movieId: Int) : Parcelable
