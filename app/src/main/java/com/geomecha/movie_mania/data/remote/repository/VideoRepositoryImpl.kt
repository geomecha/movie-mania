package com.geomecha.movie_mania.data.remote.repository

import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.mapper.toLocal
import com.geomecha.movie_mania.data.remote.api.ApiService
import com.geomecha.movie_mania.data.remote.mapper.toEntity
import com.geomecha.movie_mania.data.remote.model.VideoListResponse
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository
import retrofit2.Response

class VideoRepositoryImpl(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : VideoRepository {

    override suspend fun getVideosRemote(page: Int): List<Video> {
        val response = apiService.getVideoList(page)
        return if (response.isSuccessful) getDataFromSuccessResult(response)
        else emptyList()
    }


    override suspend fun getMoviesListMaxVote(page: Int): List<Video> {
        val response = apiService.getMovieMaxVote(page)
        return if (response.isSuccessful) getDataFromSuccessResult(response)
        else emptyList()
    }

    override suspend fun getMoviesMaxCountVote(page: Int): List<Video> {
        val response = apiService.getMoviesMaxCountVote(page)
        return if (response.isSuccessful) getDataFromSuccessResult(response)
        else emptyList()
    }

    override suspend fun getNew(page: Int, targetYear: Int): List<Video> {
        val response = apiService.getNew(page, targetYear)
        return if (response.isSuccessful) getDataFromSuccessResult(response)
        else emptyList()
    }

    private suspend fun getDataFromSuccessResult(response: Response<VideoListResponse>): List<Video> {
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
        return emptyList()
    }
}