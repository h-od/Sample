package com.hughod.common.models.network

import com.google.gson.annotations.SerializedName

data class Movies(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<Result>
) {
    data class Result(
        val popularity: Double,
        val id: Int,
        val video: Boolean,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("vote_average")
        val voteAverage: Double,
        val title: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        val adult: Boolean,
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("backdrop_path")
        val backdropPath: String?
    )
}
