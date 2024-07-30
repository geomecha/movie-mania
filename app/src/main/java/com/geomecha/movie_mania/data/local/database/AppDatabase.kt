package com.geomecha.movie_mania.data.local.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.geomecha.movie_mania.data.local.converter.ListGenreIdsConverter
import com.geomecha.movie_mania.data.local.dao.VideoDao
import com.geomecha.movie_mania.data.local.entity.VideoLocal
import com.geomecha.movie_mania.data.local.entity.FavoriteLocal

@Database(
    entities = [
        VideoLocal::class,
        FavoriteLocal::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    exportSchema = true
)

@TypeConverters(
    value = [
        ListGenreIdsConverter::class
    ]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}