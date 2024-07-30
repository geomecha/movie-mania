package com.geomecha.movie_mania.data.local

import com.geomecha.movie_mania.data.local.entity.VideoLocal
import com.geomecha.movie_mania.domain.model.Video

interface LocalDataSource {
    suspend fun getAllVideos(): List<Video>
    suspend fun getFavouriteVideos(): List<VideoLocal>
    suspend fun insertVideo(videos: List<VideoLocal>)
    suspend fun deleteVideo(video: VideoLocal)
}