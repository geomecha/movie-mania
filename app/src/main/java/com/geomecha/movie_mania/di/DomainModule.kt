package com.geomecha.movie_mania.di

import com.geomecha.movie_mania.domain.usecase.AddToFavouriteUseCase
import com.geomecha.movie_mania.domain.usecase.GetFavouriteListUseCase
import com.geomecha.movie_mania.domain.usecase.GetMoviesListMaxVoteUseCase
import com.geomecha.movie_mania.domain.usecase.GetMoviesMaxCountVoteUseCase
import com.geomecha.movie_mania.domain.usecase.GetNewMoviesUseCase
import com.geomecha.movie_mania.domain.usecase.GetVideoListUseCase
import com.geomecha.movie_mania.domain.usecase.RemoveFromFavouriteUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetVideoListUseCase(get(), get(), get()) }
    single { GetFavouriteListUseCase(get()) }
    single { AddToFavouriteUseCase(get()) }
    single { RemoveFromFavouriteUseCase(get()) }
    single { GetMoviesListMaxVoteUseCase(get(), get(), get()) }
    single { GetMoviesMaxCountVoteUseCase(get(), get(), get()) }
    single { GetNewMoviesUseCase(get(), get(), get()) }

}