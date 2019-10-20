package com.hughod.common.models.mappers

import com.hughod.common.models.app.MovieDetailModel
import com.hughod.common.models.app.MovieListModel
import com.hughod.common.models.app.MovieModel
import com.hughod.common.models.network.Movie
import com.hughod.common.models.network.Movies

fun Movies?.map(): MovieListModel = this!!.results.map {
    MovieModel(
        id = it.id,
        name = it.title,
        overview = it.overview,
        releaseDate = it.releaseDate,
        rating = it.voteAverage,
        posterPath = it.posterPath ?: "",
        backdropPath = it.backdropPath ?: ""
    )
}

fun Movie?.map(): MovieDetailModel = MovieDetailModel(
    id = this!!.id,
    imdbId = this.imdbId,

    tagline = this.tagline,
    title = this.title,
    overview = this.overview,

    releaseDate = this.releaseDate,
    runtime = this.runtime,

    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    popularity = this.popularity,

    genres = this.genres.map { it.name },

    backdropPath = this.backdropPath,
    posterPath = this.posterPath,

    productionCompanies = this.productionCompanies.map { it.name },

    revenue = this.revenue,
    budget = this.budget,

    homepage = this.homepage
)
