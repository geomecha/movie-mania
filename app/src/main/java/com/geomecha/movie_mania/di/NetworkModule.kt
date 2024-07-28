package com.geomecha.movie_mania.di

import com.geomecha.movie_mania.data.remote.api.ApiService
import com.geomecha.movie_mania.data.remote.api.api
import com.geomecha.movie_mania.data.remote.api.httpClient
import com.geomecha.movie_mania.data.remote.api.retrofit
import com.google.gson.Gson
import org.koin.dsl.module

val networkModule = module {
    single { Gson() }
    single { api<ApiService>(retrofit(get(), httpClient(get()))) }
}