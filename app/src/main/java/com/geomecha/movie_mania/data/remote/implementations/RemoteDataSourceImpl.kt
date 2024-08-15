package com.geomecha.movie_mania.data.remote.implementations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.mapper.toLocal
import com.geomecha.movie_mania.data.remote.RemoteDataSource
import com.geomecha.movie_mania.data.remote.api.ApiService
import com.geomecha.movie_mania.data.remote.mapper.toEntity
import com.geomecha.movie_mania.data.remote.model.VideoListResponse
import com.geomecha.movie_mania.data.remote.source.VideoDataSource
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.presentation.constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RemoteDataSourceImpl(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : RemoteDataSource {

    private suspend fun getVideosRemote(page: Int): List<Video> {
        val response = apiService.getVideoList(page)
        return if (response.isSuccessful) getDataFromSuccessResult(response)
        else emptyList()
    }

    override suspend fun getVideos(): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                VideoDataSource(
                    loadMethod = { page -> getVideosRemote(page) }
                )
            }
        ).flow
    }

    override suspend fun getMoviesListMaxVotePaging(): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                VideoDataSource(
                    loadMethod = { page -> getMoviesListMaxVote(page) }
                )
            }
        ).flow
    }

    private suspend fun getMoviesListMaxVote(page: Int): List<Video> {
        val response = apiService.getMovieMaxVote(page)
        return if (response.isSuccessful) getDataFromSuccessResult(response)
        else emptyList()
    }

    override suspend fun getMoviesMaxCountVotePaging(): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                VideoDataSource(
                    loadMethod = { page -> getMoviesMaxCountVote(page) }
                )
            }
        ).flow
    }

    private suspend fun getMoviesMaxCountVote(page: Int): List<Video> {
        val response = apiService.getMoviesMaxCountVote(page)
        return if (response.isSuccessful) getDataFromSuccessResult(response)
        else emptyList()
    }


    private suspend fun getNew(page: Int, targetYear: Int): List<Video> {
        val response = apiService.getNew(page, targetYear)
        return if (response.isSuccessful) getDataFromSuccessResult(response)
        else emptyList()
    }

    override suspend fun getNewMovie(targetYear: Int): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                VideoDataSource(
                    loadMethod = { page -> getNew(page, targetYear) }
                )
            }
        ).flow
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