package com.hughod.data

import com.hughod.common.models.app.MovieDetailModel
import com.hughod.common.models.mappers.map
import com.hughod.common.models.network.Movie
import com.hughod.network.Network
import com.hughod.storage.Cache

class MovieDetailRepository(
    private val movieId: Int,
    private val network: Network.Detail,
    private val cache: Cache.Detail
) : ReadOnlyRepository<MovieDetailModel> {
    override suspend fun fetch(): MovieDetailModel = try {
        network.get(movieId.toString()).execute().body().cache()
    } catch (e: Exception) {
        cache[movieId]
    }.map()

    private fun Movie?.cache(): Movie? = this?.also { cache.add(it) } ?: cache[movieId]
}
