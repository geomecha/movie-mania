package com.geomecha.movie_mania.data.remote.source

import androidx.paging.PagingState
import com.geomecha.movie_mania.data.network.ConnectivityProvider
import com.geomecha.movie_mania.data.remote.paging.BasePagingSource
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.presentation.constants.FIRST_PAGE
import com.geomecha.movie_mania.presentation.constants.ONE_PAGE
import com.geomecha.movie_mania.presentation.constants.PAGE_SIZE

class VideoDataSource(
    private val connectivityProvider: ConnectivityProvider,
    private val apiLoadMethod: suspend (page: Int) -> List<Video>,
    private val localLoadMethod: suspend (page: Int) -> List<Video>
) : BasePagingSource<Int, Video>() {

    override suspend fun loadPaging(params: LoadParams<Int>): LoadResult<Int, Video> {

        val page = params.key ?: getInitPage()

        val currentList =
            if (connectivityProvider.isOnline())
                apiLoadMethod.invoke(page)
            else
                localLoadMethod.invoke(page)

        val nextKey = if (currentList.size < PAGE_SIZE) null else page + ONE_PAGE
        val prevKey = if (page == FIRST_PAGE) null else page - ONE_PAGE
        return LoadResult.Page(currentList, prevKey, nextKey)
    }

    override fun getInitPage(): Int = FIRST_PAGE
    override fun getRefreshKey(state: PagingState<Int, Video>) = null

}
