package com.geomecha.movie_mania.data.local.mapper

import com.geomecha.movie_mania.data.local.entity.FavoriteLocal
import com.geomecha.movie_mania.domain.model.Video

fun Video.toFavoriteLocal(): FavoriteLocal {
    return FavoriteLocal(
        videoId = this.id,
        isFavorite = true
    )
}