package com.geomecha.movie_mania.data.local.implementations

import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.dao.VideoDao
import com.geomecha.movie_mania.data.local.entity.VideoEntity

class LocalDataSourceImpl(
    private val videoDao: VideoDao
) : LocalDataSource {

    override suspend fun getAllVideos(): List<VideoEntity> {
        //return videoDao.getAllVideos()
        return emptyList()
    }

    override suspend fun getFavouriteVideos(): List<VideoEntity> {
        //return videoDao.getAllVideos().filter { it.isFavourite }
        return emptyList()
    }

    override suspend fun insertVideo(video: VideoEntity) {
        //videoDao.insertVideo(video)
    }

    override suspend fun deleteVideo(video: VideoEntity) {
        //videoDao.deleteVideoById(video.id)
    }
}