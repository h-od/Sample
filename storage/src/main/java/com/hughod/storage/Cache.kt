package com.hughod.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import com.hughod.common.models.network.Movie
import com.hughod.common.models.network.MovieDetails
import com.hughod.common.models.network.Movies
import com.hughod.storage.util.Clearable

object Cache {

    class Detail(
        private val preferences: SharedPreferences,
        private val serializer: Serializer
    ) : Clearable {

        operator fun get(id: Int): Movie? = this.data.find { it.id == id }

        fun add(movie: Movie) {
            data = ArrayList(data).also { it.add(movie) }
        }

        private var data: kotlin.collections.List<Movie>
            get() = serializer.fromString(
                preferences.getString(KEY_DETAIL, null),
                MovieDetails::class.java
            )?.list ?: listOf()
            set(value) {
                preferences.edit().putString(
                    KEY_LIST,
                    serializer.toString(value, MovieDetails::class.java)
                ).apply()
            }

        override fun clear() = preferences.edit().clear().apply()

    }

    class List(private val preferences: SharedPreferences, private val serializer: Serializer) :
        Clearable {

        var data: Movies?
            get() = serializer.fromString(
                preferences.getString(KEY_LIST, null),
                Movies::class.java
            )
            set(value) {
                if (value != null) preferences.edit().putString(
                    KEY_LIST,
                    serializer.toString(value, Movies::class.java)
                ).apply()
            }

        override fun clear() = preferences.edit().clear().apply()
    }

    private const val KEY_LIST = "KEY_ACCOUNT_ID"
    private const val KEY_DETAIL = "KEY_ACCESS_TOKEN"
}

interface Serializer {
    fun <T> fromString(input: String?, clazz: Class<T>): T?
    fun <T> toString(input: Any?, clazz: Class<T>): String?
}

class GsonSerializer(private val gson: Gson) : Serializer {

    override fun <T> fromString(input: String?, clazz: Class<T>): T? = gson.fromJson(input, clazz)

    override fun <T> toString(input: Any?, clazz: Class<T>): String? = if (input == null)
        null else gson.toJson(input, clazz)
}
