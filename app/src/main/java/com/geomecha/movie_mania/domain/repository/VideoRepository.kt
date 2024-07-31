package com.geomecha.movie_mania.domain.repository

import com.geomecha.movie_mania.domain.model.Video

interface VideoRepository {
    suspend fun getVideosRemote(page: Int): List<Video>
    suspend fun getMoviesListMaxVote(page: Int): List<Video>
    suspend fun getMoviesMaxCountVote(page: Int): List<Video>
    suspend fun getNew(page: Int, targetYear: Int): List<Video>
}