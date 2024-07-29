package com.geomecha.movie_mania.data.remote.model

import com.google.gson.annotations.SerializedName

data class VideoListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val videoResponses: List<VideoResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)