package com.geomecha.movie_mania.domain.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class Video(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    private val _favoriteSource = MutableStateFlow(false)
    val favoriteLive: Flow<Boolean> = _favoriteSource

    var isFavorite: Boolean = false
        get() = _favoriteSource.value
        set(value) {
            field = value
            _favoriteSource.value = field
        }
}