package com.geomecha.movie_mania.data.remote.repository

import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.remote.api.ApiService
import com.geomecha.movie_mania.domain.repository.VideoRepository

class VideoRepositoryImpl (
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : VideoRepository {

    override suspend fun getVideoList(): List<Any> {
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