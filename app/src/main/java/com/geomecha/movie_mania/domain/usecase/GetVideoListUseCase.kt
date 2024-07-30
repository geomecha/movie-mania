package com.geomecha.movie_mania.domain.usecase

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.network.ConnectivityProvider
import com.geomecha.movie_mania.data.remote.source.VideoDataSource
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

class GetVideoListUseCase(
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
                    connectivityProvider,
                    videoRepository,
                    localDataSource
                )
            }
        ).flow
    }

}