package com.geomecha.movie_mania.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
class FavoriteLocal(
    @PrimaryKey
    val videoId: Int,
    val isFavorite: Boolean
)