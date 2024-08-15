package com.geomecha.movie_mania.data.remote

import androidx.paging.PagingData
import com.geomecha.movie_mania.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getVideos(): Flow<PagingData<Video>>
    suspend fun getMoviesListMaxVotePaging(): Flow<PagingData<Video>>
    suspend fun getMoviesMaxCountVotePaging(): Flow<PagingData<Video>>
    suspend fun getNewMovie(targetYear: Int): Flow<PagingData<Video>>
}