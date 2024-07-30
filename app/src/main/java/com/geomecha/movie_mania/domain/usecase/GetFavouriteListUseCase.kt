package com.geomecha.movie_mania.domain.usecase

import android.nfc.tech.MifareUltralight
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geomecha.movie_mania.data.local.LocalDataSource
import com.geomecha.movie_mania.data.remote.source.FavouritesDataSource
import com.geomecha.movie_mania.domain.model.Video
import kotlinx.coroutines.flow.Flow

class GetFavouriteListUseCase(
    private val localDataSource: LocalDataSource
) {

    operator fun invoke(): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(
                pageSize = MifareUltralight.PAGE_SIZE,
            ),
            pagingSourceFactory = {
                FavouritesDataSource(
                    localDataSource
                )
            }
        ).flow
    }

}