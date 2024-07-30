package com.geomecha.movie_mania.data.remote.source

import androidx.paging.PagingState
import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.remote.paging.BasePagingSource
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.presentation.constants.FIRST_PAGE
import com.geomecha.movie_mania.presentation.constants.ONE_PAGE
import com.geomecha.movie_mania.presentation.constants.PAGE_SIZE

class FavouritesDataSource(
    private val localDataSource: LocalDataSource
) : BasePagingSource<Int, Video>() {

    override suspend fun loadPaging(params: LoadParams<Int>): LoadResult<Int, Video> {
        val page = params.key ?: getInitPage()

        val currentList = localDataSource.getFavouriteVideos(page, PAGE_SIZE)

        val nextKey = if (currentList.size < PAGE_SIZE) null else page + ONE_PAGE
        val prevKey = if (page == FIRST_PAGE) null else page - ONE_PAGE

        return LoadResult.Page(currentList, prevKey, nextKey)
    }

    override fun getInitPage(): Int = FIRST_PAGE
    override fun getRefreshKey(state: PagingState<Int, Video>) = null


}
