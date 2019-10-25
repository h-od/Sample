package com.hughod.interaction.entities

data class DetailEntity(
    val id: Int = -1,
    val imdbId: String = "",

    val overview: String = "",

    val name: String = "",
    val tagLine: String = "",
    val backdropPath: String = "",

    val budget: Int = -1,
    val revenue: Int = -1,
    val runtime: Int = -1,
    val releaseDate: String = "",

    val genres: List<GenreEntity> = emptyList(),
    val homepage: String = "",

    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0,
    val voteCount: Int = -1,

    val productionCompanies: List<ProductionCompanyEntity> = emptyList(),
    val productionCountryNames: List<String> = emptyList()
) {
    fun hasBudget(): Boolean = this.budget > 0
    fun hasRevenue(): Boolean = this.revenue > 0

    data class GenreEntity(
        val id: Int,
        val name: String
    )

    data class ProductionCompanyEntity(
        val id: Int,
        val name: String,
        val logoPath: String
    )
}
