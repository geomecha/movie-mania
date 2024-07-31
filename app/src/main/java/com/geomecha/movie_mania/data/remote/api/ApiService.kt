package com.geomecha.movie_mania.data.remote.api

import com.geomecha.movie_mania.data.remote.model.VideoListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getVideoList(
        @Query(AppQuery.page)
        page: Int
    ): Response<VideoListResponse>

    @GET("discover/movie")
    suspend fun getMoviesMaxCountVote(
        @Query(AppQuery.page)
        page: Int,
        @Query(AppQuery.voteCountGTE)
        date: Float = AppQuery.voteCount
    ): Response<VideoListResponse>

    @GET("discover/movie")
    suspend fun getMovieMaxVote(
        @Query(AppQuery.page)
        page: Int,
        @Query(AppQuery.voteAverageGTE)
        date: Float = AppQuery.voteAverageSeven
    ): Response<VideoListResponse>

    @GET("discover/movie")
    suspend fun getNew(
        @Query(AppQuery.page)
        page: Int,
        @Query(AppQuery.primaryReleaseYear)
        date: Int,
        @Query(AppQuery.sortBy)
        sortBy: String = AppQuery.primaryReleaseYearASC
    ): Response<VideoListResponse>
}