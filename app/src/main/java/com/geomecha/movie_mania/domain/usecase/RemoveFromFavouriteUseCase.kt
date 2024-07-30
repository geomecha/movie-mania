package com.geomecha.movie_mania.domain.usecase

import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.domain.model.Video

class RemoveFromFavouriteUseCase(private val localDataSource: LocalDataSource) {

    suspend operator fun invoke(video: Video) {
        localDataSource.removeFromFavourite(video.id)
    }

}