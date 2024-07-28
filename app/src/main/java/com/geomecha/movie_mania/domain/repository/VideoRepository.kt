package com.geomecha.movie_mania.domain.repository

interface VideoRepository {
    suspend fun getVideoList(): List<Any>
    suspend fun getFavouriteList(): List<Any>
    suspend fun addToFavourite(movie: Any)
    suspend fun removeFromFavourite(movie: Any)
    suspend fun getMoviesListMaxVote(): List<Any>
    suspend fun getMoviesMaxCountVote(): List<Any>
}