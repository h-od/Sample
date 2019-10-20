package com.hughod.common.models.app

data class MovieDetailModel(
    val id: Int,
    val imdbId: String,

    val tagline: String,
    val title: String,
    val overview: String,

    val releaseDate: String,
    val runtime: Int,

    val voteAverage: Double,
    val voteCount: Int,
    val popularity: Double,

    val genres: List<String>,

    val backdropPath: String,
    val posterPath: String,

    val productionCompanies: List<String>,

    val revenue: Int,
    val budget: Int,

    val homepage: String
)
