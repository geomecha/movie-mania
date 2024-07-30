package com.geomecha.movie_mania.data.remote.source

import android.util.Log
import androidx.paging.PagingState
import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.network.ConnectivityProvider
import com.geomecha.movie_mania.data.remote.paging.BasePagingSource
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.repository.VideoRepository
import com.geomecha.movie_mania.presentation.constants.FIRST_PAGE
import com.geomecha.movie_mania.presentation.constants.ONE_PAGE
import com.geomecha.movie_mania.presentation.constants.PAGE_SIZE

class VideoDataSource(
    private val connectivityProvider: ConnectivityProvider,
    private val videoRepository: VideoRepository,
    private val localDataSource: LocalDataSource
) : BasePagingSource<Int, Video>() {

    override suspend fun loadPaging(params: LoadParams<Int>): LoadResult<Int, Video> {
        val page = params.key ?: getInitPage()
        return if (connectivityProvider.isOnline()) {
            val currentList = videoRepository.getVideoList(page)

            val nextKey = if (currentList.size < PAGE_SIZE) null else page + ONE_PAGE
            val prevKey = if (page == FIRST_PAGE) null else page - ONE_PAGE
            LoadResult.Page(currentList, prevKey, nextKey)
        } else {
            val currentList = localDataSource.getAllVideos()
            LoadResult.Page(currentList, null, null)
        }
    }

    override fun getInitPage(): Int = FIRST_PAGE
    override fun getRefreshKey(state: PagingState<Int, Video>) = null


}
