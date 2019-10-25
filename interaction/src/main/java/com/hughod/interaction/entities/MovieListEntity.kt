package com.hughod.interaction.entities

data class MovieListEntity(
    val movies: List<MovieEntity> = listOf(),
    val status: EntityStatus = EntityStatus.NOT_FOUND
) {

    data class MovieEntity(
        val id: Int = -1,
        val name: String = "",
        val overview: String = "",
        val releaseDate: String = "",//todo Date
        val rating: Double = 0.0,
        val posterPath: String = "",
        val backdropPath: String = "",
        val status: EntityStatus = EntityStatus.NOT_FOUND
    )
}
