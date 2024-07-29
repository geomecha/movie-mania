package com.geomecha.movie_mania.data.remote.mapper

import com.geomecha.movie_mania.data.remote.mapper.extension.orFalse
import com.geomecha.movie_mania.data.remote.mapper.extension.orZero
import com.geomecha.movie_mania.data.remote.model.VideoResponse
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.presentation.constants.IMG_URL

fun VideoResponse.toEntity(): Video {
    return Video(
        adult = adult.orFalse(),
        backdropPath = "$IMG_URL${backdropPath}",
        genreIds = genreIds.orEmpty(),
        id = id,
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity.orZero(),
        posterPath = "$IMG_URL${posterPath}",
        releaseDate = releaseDate.orEmpty(),
        title = title.orEmpty(),
        video = video.orFalse(),
        voteAverage = voteAverage.orZero(),
        voteCount = voteCount.orZero()
    )
}