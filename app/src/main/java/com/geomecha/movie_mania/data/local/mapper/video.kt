package com.geomecha.movie_mania.data.local.mapper

import com.geomecha.movie_mania.data.local.entity.VideoLocal
import com.geomecha.movie_mania.data.remote.mapper.extension.orFalse
import com.geomecha.movie_mania.data.remote.mapper.extension.orZero
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.presentation.constants.IMG_URL

fun VideoLocal.toEntity(): Video {
    return Video(
        adult = adult.orFalse(),
        backdropPath = "$IMG_URL${backdropPath}",
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity.orZero(),
        posterPath = "$IMG_URL${posterPath}",
        releaseDate = releaseDate,
        title = title,
        video = video.orFalse(),
        voteAverage = voteAverage.orZero(),
        voteCount = voteCount.orZero()
    )
}

fun Video.toLocal(): VideoLocal {
    return VideoLocal(
        adult = adult.orFalse(),
        backdropPath = "$IMG_URL${backdropPath}",
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity.orZero(),
        posterPath = "$IMG_URL${posterPath}",
        releaseDate = releaseDate,
        title = title,
        video = video.orFalse(),
        voteAverage = voteAverage.orZero(),
        voteCount = voteCount.orZero()
    )
}