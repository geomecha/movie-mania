package com.geomecha.movie_mania.domain.repository

import androidx.paging.PagingData
import com.geomecha.movie_mania.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun getMoviesListMaxVote(): Flow<PagingData<Video>>
    suspend fun getMoviesMaxCountVote(): Flow<PagingData<Video>>
    suspend fun getNew(targetYear: Int): Flow<PagingData<Video>>
    suspend fun getVideoList(): Flow<PagingData<Video>>
    suspend fun getFavouriteVideos(): Flow<PagingData<Video>>
    suspend fun addToFavourite(movie: Video)
    suspend fun removeFromFavourite(videoId: Int)
}