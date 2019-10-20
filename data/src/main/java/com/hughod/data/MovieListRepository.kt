package com.hughod.data

import com.hughod.common.models.app.MovieListModel
import com.hughod.common.models.mappers.map
import com.hughod.common.models.network.Movies
import com.hughod.network.Network
import com.hughod.storage.Cache

class MovieListRepository(
    private val network: Network.List,
    private val cache: Cache.List
) : ReadOnlyRepository<MovieListModel> {
    override suspend fun fetch(): MovieListModel = try {
        network.get().execute().body().cache()
    } catch (e: Exception) {
        cache.data
    }.map()

    private fun Movies?.cache(): Movies? = this?.also { cache.data = it } ?: cache.data
}
