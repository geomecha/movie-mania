package com.geomecha.movie_mania.di

import com.geomecha.movie_mania.data.remote.api.ApiService
import com.geomecha.movie_mania.data.remote.api.QInterceptor
import com.geomecha.movie_mania.data.remote.api.api
import com.geomecha.movie_mania.data.remote.api.httpClient
import com.geomecha.movie_mania.data.remote.api.retrofit
import com.google.gson.Gson
import org.koin.dsl.module

val networkModule = module {
    single { Gson() }
    single { QInterceptor() }
    single { httpClient(get()) }
    single { retrofit(get(), get()) }
    single { api<ApiService>(get()) }
}