package com.hughod.network.config

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

object Interceptors {
    fun apiKey(apiKey: String) = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            val requestUrl: HttpUrl =
                request.url.newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()

            return chain.proceed(request.newBuilder().url(requestUrl).build())
        }
    }

    val logging: Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}