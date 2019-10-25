package com.hughod.data

import com.hughod.data.data_source.DataSource
import com.hughod.data.data_source.ReadOnlyDataSource
import com.hughod.interaction.data.DataProvider
import com.hughod.interaction.entities.DetailEntity

class MovieDetailProvider(
    private val movieId: Int,
    private val network: ReadOnlyDataSource<DetailEntity>,
    private val cache: DataSource<DetailEntity>
) : DataProvider<DetailEntity> {
    override suspend fun fetch(): DetailEntity = fetchFromNetworkOrCache() ?: DetailEntity()

    private suspend fun fetchFromNetworkOrCache(): DetailEntity? = try {
        network.get(movieId)?.also { cache.add(it) } ?: cache.get(movieId)
    } catch (e: Exception) {
        e.printStackTrace()
        cache.get(movieId)
    }
}
