package com.geomecha.movie_mania.data.remote.repository

import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.mapper.toLocal
import com.geomecha.movie_mania.data.remote.api.ApiService
import com.geomecha.movie_mania.data.remote.mapper.toEntity
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository

class VideoRepositoryImpl(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : VideoRepository {

    override suspend fun getVideoList(page: Int): List<Video> {
        val response = apiService.getVideoList(page)
        when (response.isSuccessful) {
            true -> {
                response.body()?.let { dataModels ->
                    val videos = dataModels.videoResponses.map {
                        it.toEntity().let { video ->
                            video.isFavorite = localDataSource.isFavourite(video.id)
                            video
                        }
                    }
                    val videosLocal = videos.map { it.toLocal() }
                    localDataSource.insertVideo(videosLocal)

                    return videos
                }
            }

            false -> return emptyList()
        }
        return emptyList()
    }

    override suspend fun getFavouriteList(): List<Any> {
        return emptyList()
    }

    override suspend fun addToFavourite(movie: Any) {
    }

    override suspend fun removeFromFavourite(movie: Any) {
    }

    override suspend fun getMoviesListMaxVote(): List<Any> {
        return emptyList()
    }

    override suspend fun getMoviesMaxCountVote(): List<Any> {
        return emptyList()
    }
}