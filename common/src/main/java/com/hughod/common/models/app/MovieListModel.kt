package com.hughod.common.models.app

import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

typealias MovieListModel = List<MovieModel>

@Parcelize
data class MovieModel(
    val id: Int,
    val name: String,
    val overview: String,
    val releaseDate: String,
    val rating: Double,
    val posterPath: String,
    val backdropPath: String
) : Parcelable {
    fun toBundle(): Bundle = Bundle().apply { this.putParcelable(MOVIE_KEY, this@MovieModel) }

    companion object {
        fun fromBundle(bundle : Bundle?): MovieModel? = bundle?.getParcelable(MOVIE_KEY)
    }
}

const val MOVIE_KEY = "MOVIE_KEY"
