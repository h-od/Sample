package com.hughod.storage.data_source

import android.content.SharedPreferences
import com.hughod.data.data_source.DataSource
import com.hughod.data.data_source.mapper.DataMapper
import com.hughod.interaction.entities.MovieListEntity
import com.hughod.storage.dao.MovieListDao
import com.hughod.storage.util.Clearable
import com.hughod.storage.util.Serializer

class MovieListCache(
    private val preferences: SharedPreferences,
    private val serializer: Serializer,
    private val toDomainMapper: DataMapper<MovieListDao, MovieListEntity>,
    private val toStorageMapper: DataMapper<MovieListEntity, MovieListDao>
) : DataSource<MovieListEntity>, Clearable {
    override suspend fun get(id: Int): MovieListEntity? = toDomainMapper.map(
        serializer.fromString(
            preferences.getString(KEY_LIST, null),
            MovieListDao::class.java
        )
    )

    override fun add(item: MovieListEntity) {
        toStorageMapper.map(item)?.let {
            preferences.edit().putString(
                KEY_LIST,
                serializer.toString(it, MovieListDao::class.java)
            ).apply()
        }
    }

    override fun clear() = preferences.edit().clear().apply()

    companion object {
        private const val KEY_LIST = "KEY_LIST"
    }
}