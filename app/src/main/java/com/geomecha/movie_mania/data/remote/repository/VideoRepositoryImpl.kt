package com.geomecha.movie_mania.data.remote.repository

import androidx.paging.PagingData
import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.local.mapper.toFavoriteLocal
import com.geomecha.movie_mania.data.network.ConnectivityProvider
import com.geomecha.movie_mania.data.remote.RemoteDataSource
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

class VideoRepositoryImpl(
    private val connectivityProvider: ConnectivityProvider,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : VideoRepository {

    override suspend fun getMoviesListMaxVote(): Flow<PagingData<Video>> {
        return if (connectivityProvider.isOnline())
            remoteDataSource.getMoviesListMaxVotePaging()
        else localDataSource.getVideosLocal()
    }

    override suspend fun getMoviesMaxCountVote(): Flow<PagingData<Video>> {
        return if (connectivityProvider.isOnline())
            remoteDataSource.getMoviesMaxCountVotePaging()
        else localDataSource.getVideosLocal()
    }

    override suspend fun getNew(targetYear: Int): Flow<PagingData<Video>> {
        return if (connectivityProvider.isOnline())
            remoteDataSource.getNewMovie(targetYear)
        else localDataSource.getVideosLocal()
    }

    override suspend fun getVideoList(): Flow<PagingData<Video>> {
        return if (connectivityProvider.isOnline())
            remoteDataSource.getVideos()
        else localDataSource.getVideosLocal()
    }

    override suspend fun getFavouriteVideos(): Flow<PagingData<Video>> {
        return localDataSource.getFavouriteVideos()
    }


    override suspend fun addToFavourite(movie: Video) {
        localDataSource.addToFavourite(movie.toFavoriteLocal())
    }

    override suspend fun removeFromFavourite(videoId: Int) {
        localDataSource.removeFromFavourite(videoId)
    }

}