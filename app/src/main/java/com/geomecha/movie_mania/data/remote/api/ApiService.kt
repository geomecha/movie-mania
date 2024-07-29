package com.geomecha.movie_mania.data.remote.api

import com.geomecha.movie_mania.data.remote.model.VideoListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getVideoList(
        @Query(AppQuery.page)
        page: Int,
    ): Response<VideoListResponse>

    @GET("favorites")
    suspend fun getFavouriteList(): List<Any>

    @POST("favorites")
    suspend fun addToFavourite(@Body item: Any)

    @DELETE("favorites/{id}")
    suspend fun removeFromFavourite(@Path("id") id: String): Response<Unit>

    @GET("movies/max-vote")
    suspend fun getMoviesMaxCountVote(): List<Any>

    @GET("movies/max-vote/{count}")
    suspend fun getMovieListMaxVote(@Path("count") count: Int): List<Any>
}