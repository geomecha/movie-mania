package com.geomecha.movie_mania.data.local.implementations

import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.dao.VideoDao
import com.geomecha.movie_mania.data.local.entity.FavoriteLocal
import com.geomecha.movie_mania.data.local.entity.VideoLocal
import com.geomecha.movie_mania.data.local.mapper.toEntity
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.presentation.constants.SINGLE_ITEM

class LocalDataSourceImpl(
    private val videoDao: VideoDao
) : LocalDataSource {

    override suspend fun getAllVideos(page: Int, pageSize: Int): List<Video> {
        val row = (page - SINGLE_ITEM) * pageSize
        return videoDao.getAllVideos(row, pageSize).map { it.toEntity() }
    }

    override suspend fun getFavouriteVideos(page: Int, pageSize: Int): List<Video> {
        val row = (page - SINGLE_ITEM) * pageSize

        return videoDao.getFavouriteVideos(row, pageSize).map {
            it.toEntity().let { video ->
                video.isFavorite = true
                video
            }
        }
    }

    override suspend fun insertVideo(videos: List<VideoLocal>) {
        videoDao.insertVideo(videos)
    }

    override suspend fun addToFavourite(favouriteVideo: FavoriteLocal) {
        videoDao.insertFavoriteVideo(favouriteVideo)
    }

    override suspend fun removeFromFavourite(videoId: Int) {
        videoDao.removeFavoriteVideoById(videoId)
    }

    override suspend fun isFavourite(videoId: Int): Boolean {
        return videoDao.isVideoInFavorites(videoId)
    }
}