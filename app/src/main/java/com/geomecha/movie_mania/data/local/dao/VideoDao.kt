package com.geomecha.movie_mania.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geomecha.movie_mania.data.local.entity.FavoriteLocal
import com.geomecha.movie_mania.data.local.entity.VideoLocal

@Dao
interface VideoDao {

    @Query("SELECT * FROM videos LIMIT :pageSize OFFSET :offset")
    suspend fun getAllVideos(offset: Int, pageSize: Int): List<VideoLocal>

    @Query(
        """
        SELECT videos.* FROM videos
        INNER JOIN favorites ON videos.id = favorites.videoId
        WHERE favorites.isFavorite = 1 LIMIT :pageSize OFFSET :offset
        """
    )
    suspend fun getFavouriteVideos(offset: Int, pageSize: Int): List<VideoLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videos: List<VideoLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteVideo(video: FavoriteLocal)

    @Query("DELETE FROM favorites WHERE videoId = :videoId")
    suspend fun removeFavoriteVideoById(videoId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE videoId = :videoId)")
    suspend fun isVideoInFavorites(videoId: Int): Boolean

}
