package com.geomecha.movie_mania.domain.usecase

import androidx.paging.PagingData
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class GetNewMoviesUseCase(
    private val videoRepository: VideoRepository
) {

    suspend operator fun invoke(): Flow<PagingData<Video>> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return videoRepository.getNew(currentYear)
    }
}