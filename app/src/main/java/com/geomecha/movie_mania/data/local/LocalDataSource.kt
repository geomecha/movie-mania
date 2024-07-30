package com.geomecha.movie_mania.data.local

import com.geomecha.movie_mania.data.local.entity.VideoLocal

interface LocalDataSource {
    suspend fun getAllVideos(): List<VideoLocal>
    suspend fun getFavouriteVideos(): List<VideoLocal>
    suspend fun insertVideo(videos: List<VideoLocal>)
    suspend fun deleteVideo(video: VideoLocal)
}