package com.hughod.network.mapper

import com.hughod.data.data_source.mapper.DataMapper
import com.hughod.interaction.entities.EntityStatus
import com.hughod.interaction.entities.MovieListEntity
import com.hughod.network.dto.MovieListDto
import com.hughod.network.dto.ResultDto

class ListDtoToEntityMapper : DataMapper<MovieListDto, MovieListEntity> {

    private val defaultEntity = MovieListEntity.MovieEntity()

    override fun map(data: MovieListDto?): MovieListEntity? = if (data != null) {
        val status =
            if (data.results.isNullOrEmpty()) EntityStatus.NOT_FOUND else EntityStatus.SUCCESS

        val movies = data.results?.map {
            if (it != null) MovieListEntity.MovieEntity(
                id = it.id ?: defaultEntity.id,
                name = it.title ?: defaultEntity.name,
                overview = it.overview ?: defaultEntity.overview,
                releaseDate = it.releaseDate ?: defaultEntity.releaseDate,//todo Date
                rating = it.popularity ?: defaultEntity.rating,
                posterPath = it.posterPath ?: defaultEntity.posterPath,
                status = EntityStatus.SUCCESS
            ) else defaultEntity
        }

        MovieListEntity(movies ?: listOf(), status)
    } else null
}

class ListEntityToDtoMapper : DataMapper<MovieListEntity, MovieListDto> {

    override fun map(data: MovieListEntity?): MovieListDto? = if (data != null) {
        MovieListDto(results = data.movies.map {
            ResultDto(
                id = it.id,
                title = it.name,
                overview = it.overview,
                releaseDate = it.releaseDate,//todo Date
                popularity = it.rating,
                posterPath = it.posterPath
            )
        })
    } else null
}
