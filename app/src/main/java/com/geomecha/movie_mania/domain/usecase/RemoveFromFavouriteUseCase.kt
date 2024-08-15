package com.geomecha.movie_mania.domain.usecase

import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository

class RemoveFromFavouriteUseCase(private val videoRepository: VideoRepository) {

    suspend operator fun invoke(video: Video) {
        videoRepository.removeFromFavourite(video.id)
    }

}