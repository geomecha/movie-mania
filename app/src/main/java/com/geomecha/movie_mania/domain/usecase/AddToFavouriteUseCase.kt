package com.geomecha.movie_mania.domain.usecase

import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository

class AddToFavouriteUseCase(private val videoRepository: VideoRepository) {

    suspend operator fun invoke(movie: Video) {
        videoRepository.addToFavourite(movie)
    }

}