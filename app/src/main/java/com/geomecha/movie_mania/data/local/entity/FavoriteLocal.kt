package com.geomecha.movie_mania.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
class FavoriteLocal(
    @PrimaryKey
    @ColumnInfo(name = "videoId")
    val videoId: Int,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
)