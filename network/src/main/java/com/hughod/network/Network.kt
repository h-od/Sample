package com.hughod.network

import com.hughod.network.config.RetrofitBuilder
import com.hughod.network.dto.DetailDto
import com.hughod.network.dto.MovieListDto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class Network(url: String, apiKey: String) {
    private val retrofit: Retrofit = RetrofitBuilder.build(url, apiKey)

    val movieListService: List
        get() = retrofit.create<List>(List::class.java)

    val movieDetailService: Detail
        get() = retrofit.create<Detail>(Detail::class.java)

    //todo
    interface List {
        @GET("discover/movie")
        fun get(/*@Query("sort_by") sort: String = "popularity.des"*/): Call<MovieListDto>

        @GET("discover/movie/top-rated")
        fun getTopRated(/*@Query("sort_by") sort: String = "popularity.des"*/): Call<MovieListDto>
    }

    interface Detail {
        @GET("movie/{$PATH_ID}")
        fun get(@Path(PATH_ID) id: String): Call<DetailDto>
    }

    companion object {
        private const val PATH_ID = "id"
    }
}
