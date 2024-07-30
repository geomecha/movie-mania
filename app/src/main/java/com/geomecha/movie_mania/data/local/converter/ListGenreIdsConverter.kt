package com.geomecha.movie_mania.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListGenreIdsConverter {

    @TypeConverter
    fun from(data: List<Int>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun to(data: String): List<Int> {
        val collectionType: Type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(data, collectionType)
    }
}
