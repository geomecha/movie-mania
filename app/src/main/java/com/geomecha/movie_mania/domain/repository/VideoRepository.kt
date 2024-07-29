package com.geomecha.movie_mania.domain.repository

import com.geomecha.movie_mania.domain.model.Video

interface VideoRepository {
    suspend fun getVideoList(page: Int): List<Video>
    suspend fun getFavouriteList(): List<Any>
    suspend fun addToFavourite(movie: Any)
    suspend fun removeFromFavourite(movie: Any)
    suspend fun getMoviesListMaxVote(): List<Any>
    suspend fun getMoviesMaxCountVote(): List<Any>
}