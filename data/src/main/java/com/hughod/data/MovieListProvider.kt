package com.hughod.data

import com.hughod.data.data_source.DataSource
import com.hughod.data.data_source.ReadOnlyDataSource
import com.hughod.interaction.data.DataProvider
import com.hughod.interaction.entities.MovieListEntity

class MovieListProvider(
    private val network: ReadOnlyDataSource<MovieListEntity>,
    private val cache: DataSource<MovieListEntity>
) : DataProvider<MovieListEntity> {
    override suspend fun fetch(): MovieListEntity = fetchFromNetworkOrCache() ?: MovieListEntity()

    private suspend fun fetchFromNetworkOrCache(): MovieListEntity? = try {
        network.get()?.also { cache.add(it) } ?: cache.get()
    } catch (e: Exception) {
        e.printStackTrace()
        cache.get()
    }
}
