package com.hughod.network.data_source

import com.hughod.data.data_source.ReadOnlyDataSource
import com.hughod.data.data_source.mapper.DataMapper
import com.hughod.interaction.entities.DetailEntity
import com.hughod.network.Network
import com.hughod.network.dto.DetailDto

class MovieDetailNetworkService(
    private val network: Network,
    private val mapper: DataMapper<DetailDto, DetailEntity>
) : ReadOnlyDataSource<DetailEntity> {
    override suspend fun get(id: Int): DetailEntity? =
        mapper.map(network.movieDetailService.get(id.toString()).execute().body())
}
