package com.geomecha.movie_mania.data.local

import androidx.paging.PagingData
import com.geomecha.movie_mania.data.local.entity.FavoriteLocal
import com.geomecha.movie_mania.data.local.entity.VideoLocal
import com.geomecha.movie_mania.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getVideosLocal(): Flow<PagingData<Video>>
    suspend fun insertVideo(videos: List<VideoLocal>)
    suspend fun addToFavourite(favouriteVideo: FavoriteLocal)
    suspend fun removeFromFavourite(videoId: Int)
    suspend fun isFavourite(videoId: Int): Boolean
    suspend fun getFavouriteVideos(): Flow<PagingData<Video>>
}