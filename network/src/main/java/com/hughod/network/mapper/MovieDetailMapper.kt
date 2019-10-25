package com.hughod.network.mapper

import com.hughod.data.data_source.mapper.DataMapper
import com.hughod.interaction.entities.DetailEntity
import com.hughod.network.dto.DetailDto
import com.hughod.network.dto.GenreDto
import com.hughod.network.dto.ProductionCompanyDto
import com.hughod.network.dto.ProductionCountryDto

class DetailDtoToEntityMapper : DataMapper<DetailDto, DetailEntity> {

    private val default = DetailEntity()

    override fun map(data: DetailDto?): DetailEntity? = if (data != null) DetailEntity(
        id = data.id ?: default.id,

        imdbId = data.imdbId ?: default.imdbId,

        overview = data.overview ?: default.overview,

        name = data.title ?: default.name,
        tagLine = data.tagline ?: default.tagLine,
        backdropPath = data.backdropPath ?: default.backdropPath,

        budget = data.budget ?: default.budget,
        revenue = data.revenue ?: default.revenue,
        runtime = data.runtime ?: default.runtime,
        releaseDate = data.releaseDate ?: default.releaseDate,

        genres = data.genres?.map {
            DetailEntity.GenreEntity(
                id = it?.id ?: -1,
                name = it?.name ?: ""
            )
        } ?: default.genres,

        homepage = data.homepage ?: default.homepage,

        popularity = data.popularity ?: default.popularity,
        voteAverage = data.voteAverage ?: default.voteAverage,
        voteCount = data.voteCount ?: default.voteCount,

        productionCompanies = data.productionCompanies?.map {
            DetailEntity.ProductionCompanyEntity(
                id = it?.id ?: default.id,
                name = it?.name ?: "",
                logoPath = it?.logoPath ?: ""
            )
        } ?: default.productionCompanies,
        productionCountryNames = data.productionCountries?.map { it?.name ?: "" }
            ?: default.productionCountryNames
    ) else null
}

class DetailEntityToDtoMapper : DataMapper<DetailEntity, DetailDto> {

    override fun map(data: DetailEntity?): DetailDto? = if (data != null) DetailDto(
        id = data.id,

        imdbId = data.imdbId,

        overview = data.overview,

        title = data.name,
        tagline = data.tagLine,
        backdropPath = data.backdropPath,

        budget = data.budget,
        revenue = data.revenue,
        runtime = data.runtime,
        releaseDate = data.releaseDate,

        genres = data.genres.map {
            GenreDto(id = it.id, name = it.name)
        },

        homepage = data.homepage,

        popularity = data.popularity,
        voteAverage = data.voteAverage,
        voteCount = data.voteCount,

        productionCompanies = data.productionCompanies.map {
            ProductionCompanyDto(id = it.id, name = it.name, logoPath = it.logoPath)
        },
        productionCountries = data.productionCountryNames.map { ProductionCountryDto(name = it) }
    ) else null
}
