package com.hughod.network.data_source

import com.hughod.data.data_source.ReadOnlyDataSource
import com.hughod.data.data_source.mapper.DataMapper
import com.hughod.interaction.entities.MovieListEntity
import com.hughod.network.Network
import com.hughod.network.dto.MovieListDto

class MovieListNetworkService(
    private val network: Network,
    private val mapper: DataMapper<MovieListDto, MovieListEntity>
) : ReadOnlyDataSource<MovieListEntity> {
    override suspend fun get(id: Int): MovieListEntity? =
        mapper.map(network.movieListService.get().execute().body())
}
