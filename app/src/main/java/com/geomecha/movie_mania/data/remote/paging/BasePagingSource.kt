package com.geomecha.movie_mania.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.net.ConnectException

abstract class BasePagingSource<T : Any, Y : Any> : PagingSource<T, Y>() {

    abstract suspend fun loadPaging(params: LoadParams<T>): LoadResult<T, Y>

    override fun getRefreshKey(state: PagingState<T, Y>): T? = getInitPage()

    abstract fun getInitPage(): T?

    override suspend fun load(params: LoadParams<T>): LoadResult<T, Y> {
        return try {
            loadPaging(params)
        } catch (e: Throwable) {
            Log.e("LOGS_MANIA", "Error during loadPaging", e)
            when (e) {
                is ConnectException -> throw e
                else -> LoadResult.Error(e)
            }
        }
    }
}