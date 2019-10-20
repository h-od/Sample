package com.hughod.network.config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder(url: String, apiKey: String) {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptors.apiKey(apiKey))
        .addInterceptor(Interceptors.logging)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://$url")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        fun build(url: String, apiKey: String): Retrofit = RetrofitBuilder(url, apiKey).retrofit
    }
}
