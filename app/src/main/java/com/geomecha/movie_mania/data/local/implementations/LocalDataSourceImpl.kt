package com.geomecha.movie_mania.data.local.implementations

import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.dao.VideoDao
import com.geomecha.movie_mania.data.local.entity.VideoLocal
import com.geomecha.movie_mania.data.local.mapper.toEntity
import com.geomecha.movie_mania.domain.model.Video

class LocalDataSourceImpl(
    private val videoDao: VideoDao
) : LocalDataSource {

    override suspend fun getAllVideos(): List<Video> {
        return videoDao.getAllVideos().map { it.toEntity() }
    }

    override suspend fun getFavouriteVideos(): List<VideoLocal> {
        //return videoDao.getAllVideos().filter { it.isFavourite }
        return emptyList()
    }

    override suspend fun insertVideo(videos: List<VideoLocal>) {
        videoDao.insertVideo(videos)
    }

    override suspend fun deleteVideo(video: VideoLocal) {
        //videoDao.deleteVideoById(video.id)
    }
}