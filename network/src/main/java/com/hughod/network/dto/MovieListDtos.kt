package com.hughod.network.dto

import com.google.gson.annotations.SerializedName

data class MovieListDto(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("results")
    val results: List<ResultDto?>? = null
)

data class ResultDto(
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null
)
