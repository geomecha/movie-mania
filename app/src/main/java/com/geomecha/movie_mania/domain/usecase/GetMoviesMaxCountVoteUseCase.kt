package com.geomecha.movie_mania.domain.usecase

import android.nfc.tech.MifareUltralight
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

class GetMoviesMaxCountVoteUseCase(
    private val connectivityProvider: ConnectivityProvider,
    private val videoRepository: VideoRepository,
    private val localDataSource: LocalDataSource
) {

    suspend operator fun invoke(): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(
                pageSize = MifareUltralight.PAGE_SIZE,
            ),
            pagingSourceFactory = {
                VideoDataSource(
                    connectivityProvider = connectivityProvider,
                    apiLoadMethod = { page -> videoRepository.getMoviesMaxCountVote(page) },
                    localLoadMethod = { page -> localDataSource.getVideosLocal(page, PAGE_SIZE) }
                )
            }
        ).flow
    }

}