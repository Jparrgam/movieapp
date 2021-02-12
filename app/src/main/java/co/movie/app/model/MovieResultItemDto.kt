package co.movie.app.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
        tableName = "movie",
        indices = [Index("id")]
)
data class MovieResultItemDto(
        @field:Json(name = "overview")
        val overview: String? = null,
        @field:Json(name = "original_language")
        val originalLanguage: String? = null,
        @field:Json(name = "original_title")
        val originalTitle: String? = null,
        @field:Json(name = "video")
        val video: Boolean? = null,
        @field:Json(name = "title")
        val title: String,
        @field:Json(name = "poster_path")
        val posterPath: String? = null,
        @field:Json(name = "backdrop_path")
        val backdropPath: String? = null,
        @field:Json(name = "release_date")
        val releaseDate: String? = null,
        @field:Json(name = "popularity")
        val popularity: Double? = null,
        @field:Json(name = "vote_average")
        val voteAverage: Double? = 0.0,
        @field:Json(name = "id") @PrimaryKey
        val id: Int = 0,
        @field:Json(name = "adult")
        val adult: Boolean? = null,
        @field:Json(name = "vote_count")
        val voteCount: Int? = 0
) {
    companion object {
        const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w300"
        const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
    }
}