package com.geomecha.movie_mania.data.local

import com.geomecha.movie_mania.data.local.entity.VideoEntity

interface LocalDataSource {
    suspend fun getAllVideos(): List<Any>
    suspend fun getFavouriteVideos(): List<Any>
    suspend fun insertVideo(video: VideoEntity)
    suspend fun deleteVideo(video: VideoEntity)
}