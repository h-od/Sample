package com.hughod.storage.data_source

import android.content.SharedPreferences
import com.hughod.data.data_source.DataSource
import com.hughod.data.data_source.mapper.DataMapper
import com.hughod.interaction.entities.DetailEntity
import com.hughod.storage.dao.DetailDao
import com.hughod.storage.dao.MovieDaos
import com.hughod.storage.util.Clearable
import com.hughod.storage.util.Serializer

class MovieDetailCache(
    private val preferences: SharedPreferences,
    private val serializer: Serializer,
    private val toDomainMapper: DataMapper<DetailDao, DetailEntity>,
    private val toStorageMapper: DataMapper<DetailEntity, DetailDao>
) : DataSource<DetailEntity>, Clearable {

    override suspend fun get(id: Int): DetailEntity? =
        toDomainMapper.map(this.data.find { it.id == id })

    override fun add(item: DetailEntity) {
        data = ArrayList(data).also { it.add(toStorageMapper.map(item)) }
    }

    private var data: List<DetailDao>
        get() = serializer.fromString(
            preferences.getString(KEY_DETAIL, null),
            MovieDaos::class.java
        )?.list ?: listOf()
        set(value) {
            preferences.edit().putString(
                KEY_DETAIL,
                serializer.toString(MovieDaos(value), MovieDaos::class.java)
            ).apply()
        }

    override fun clear() = preferences.edit().clear().apply()

    companion object {
        private const val KEY_DETAIL = "KEY_DETAIL"
    }
}
