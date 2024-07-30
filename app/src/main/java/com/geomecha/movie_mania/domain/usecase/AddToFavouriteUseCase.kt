package com.geomecha.movie_mania.domain.usecase

import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.mapper.toFavoriteLocal
import com.geomecha.movie_mania.domain.model.Video

class AddToFavouriteUseCase(private val localDataSource: LocalDataSource) {

    suspend operator fun invoke(movie: Video) {
        localDataSource.addToFavourite(movie.toFavoriteLocal())
    }

}