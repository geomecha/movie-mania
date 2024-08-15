package com.geomecha.movie_mania.domain.usecase

import androidx.paging.PagingData
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesMaxCountVoteUseCase(
    private val videoRepository: VideoRepository
) {

    suspend operator fun invoke(): Flow<PagingData<Video>> {
        return videoRepository.getMoviesMaxCountVote()
    }

}