package com.geomecha.movie_mania.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface VideoDao {
    suspend fun getAllVideos(): List<Any>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: Any)

    @Delete
    suspend fun deleteVideo(video: Any)
}
