package com.geomecha.movie_mania.data.local.implementations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.dao.VideoDao
import com.geomecha.movie_mania.data.local.entity.FavoriteLocal
import com.geomecha.movie_mania.data.local.entity.VideoLocal
import com.geomecha.movie_mania.data.local.mapper.toEntity
import com.geomecha.movie_mania.data.remote.source.FavouritesDataSource
import com.geomecha.movie_mania.data.remote.source.VideoDataSource
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.presentation.constants.PAGE_SIZE
import com.geomecha.movie_mania.presentation.constants.SINGLE_ITEM
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val videoDao: VideoDao
) : LocalDataSource {

    override suspend fun getVideosLocal(): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                VideoDataSource(
                    loadMethod = { page ->
                        videoDao.getAllVideos(
                            offset = (page - SINGLE_ITEM) * PAGE_SIZE,
                            pageSize = PAGE_SIZE
                        ).map { it.toEntity() }
                    }
                )
            }
        ).flow
    }

    override suspend fun getFavouriteVideos(): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                FavouritesDataSource(
                    loadMethod = { page, pageSize ->
                        getFavouriteVideosLocal(
                            page,
                            pageSize
                        )
                    }
                )
            }
        ).flow
    }


    private suspend fun getFavouriteVideosLocal(page: Int, pageSize: Int): List<Video> {
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