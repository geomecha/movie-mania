package com.geomecha.movie_mania.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.network.ConnectivityProvider
import com.geomecha.movie_mania.data.remote.source.VideoDataSource
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository
import com.geomecha.movie_mania.presentation.constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class GetMoviesListMaxVoteUseCase(
    private val connectivityProvider: ConnectivityProvider,
    private val videoRepository: VideoRepository,
    private val localDataSource: LocalDataSource
) {

    operator fun invoke(): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                VideoDataSource(
                    connectivityProvider = connectivityProvider,
                    apiLoadMethod = { page -> videoRepository.getMoviesListMaxVote(page) },
                    localLoadMethod = { page -> localDataSource.getVideosLocal(page, PAGE_SIZE) }
                )
            }
        ).flow
    }

}