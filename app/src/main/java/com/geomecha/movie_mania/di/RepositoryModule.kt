package com.geomecha.movie_mania.di

import com.geomecha.movie_mania.data.remote.repository.VideoRepositoryImpl
import com.geomecha.movie_mania.domain.repository.VideoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<VideoRepository> { VideoRepositoryImpl(get(), get()) }
}