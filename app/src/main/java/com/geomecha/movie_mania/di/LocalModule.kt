package com.geomecha.movie_mania.di

import com.geomecha.movie_mania.data.local.database.AppDatabase
import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.implementations.LocalDataSourceImpl
import org.koin.dsl.module

val localModule = module {
    single { AppDatabase.getDatabase(get()) }

    single { get<AppDatabase>().videoDao() }

    single<LocalDataSource> { LocalDataSourceImpl(get()) }
}