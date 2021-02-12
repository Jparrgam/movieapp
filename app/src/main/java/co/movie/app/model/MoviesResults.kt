package co.movie.app.model

import com.squareup.moshi.Json

data class MoviesResults(
		@field:Json(name = "page")
		var page: Int = 0,
		@field:Json(name = "total_pages")
		val totalPages: Int = 0,
		@field:Json(name = "results")
		val results: List<MovieResultItemDto> = emptyList(),
		@field:Json(name = "total_results")
		val totalResults: Int = 0
)
