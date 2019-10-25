package com.hughod.app.ui.list

import android.os.Bundle
import android.os.Parcelable
import com.hughod.interaction.entities.MovieListEntity
import kotlinx.android.parcel.Parcelize

typealias ListModel = List<MovieModel>

@Parcelize
data class MovieModel(
    val id: Int,
    val title: String,
    val body: String,
    val releaseDate: String,
    val rating: Double,
    val posterPath: String
) : Parcelable {
    fun toBundle(): Bundle = Bundle().apply { this.putParcelable(MOVIE_KEY, this@MovieModel) }

    companion object {
        fun fromBundle(bundle: Bundle?): MovieModel? = bundle?.getParcelable(MOVIE_KEY)

        fun fromDomain(domain: MovieListEntity): ListModel = domain.movies.map {
            MovieModel(
                id = it.id,
                title = it.name,
                body = it.overview,
                releaseDate = it.releaseDate,//todo format
                rating = it.rating,
                posterPath = it.posterPath
            )
        }
    }
}

const val MOVIE_KEY = "MOVIE_KEY"
