package com.geomecha.movie_mania.data.local

import com.geomecha.movie_mania.data.local.entity.FavoriteLocal
import com.geomecha.movie_mania.data.local.entity.VideoLocal
import com.geomecha.movie_mania.domain.model.Video

interface LocalDataSource {
    suspend fun getVideosLocal(page: Int, pageSize: Int): List<Video>
    suspend fun getFavouriteVideos(page: Int, pageSize: Int): List<Video>
    suspend fun insertVideo(videos: List<VideoLocal>)
    suspend fun addToFavourite(favouriteVideo: FavoriteLocal)
    suspend fun removeFromFavourite(videoId: Int)
    suspend fun isFavourite(videoId: Int): Boolean
}