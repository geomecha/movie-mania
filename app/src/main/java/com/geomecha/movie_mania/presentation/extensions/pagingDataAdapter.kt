package com.geomecha.movie_mania.presentation.extensions

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun CombinedLoadStates.isLoading(): Boolean {
    return refresh is LoadState.Loading
}