package com.geomecha.movie_mania.data.remote.api

import com.geomecha.movie_mania.presentation.constants.BASE_URL
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> api(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

fun retrofit(
    converter: Gson,
    okHttpClient: OkHttpClient,
    baseURL: String = BASE_URL
): Retrofit = Retrofit.Builder()
    .baseUrl(baseURL)
    .addConverterFactory(GsonConverterFactory.create(converter))
    .client(okHttpClient)
    .build()

fun httpClient(
    interceptor: QInterceptor,
): OkHttpClient = clientBuilder()
    .addInterceptor(loggingInterceptor())
    .addInterceptor(interceptor)
    .build()

fun clientBuilder() = OkHttpClient.Builder()
    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

fun loggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private const val TIMEOUT = 30L