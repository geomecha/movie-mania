package com.geomecha.movie_mania.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.geomecha.movie_mania.data.local.entity.VideoLocal

@Dao
interface VideoDao {
    //suspend fun getAllVideos(): List<VideoLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videos: List<VideoLocal>)

    @Delete
    suspend fun deleteVideo(video: VideoLocal)
}
