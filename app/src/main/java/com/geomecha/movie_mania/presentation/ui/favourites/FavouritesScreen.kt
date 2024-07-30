package com.geomecha.movie_mania.presentation.ui.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.geomecha.movie_mania.presentation.components.MainContent
import com.geomecha.movie_mania.presentation.extensions.shareLink
import com.geomecha.movie_mania.presentation.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavouritesScreen(navController: NavHostController, viewModel: MainViewModel) {

    val movieList = viewModel.favouriteVideoList.collectAsLazyPagingItems()

    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { movieList.refresh() })
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        contentAlignment = Alignment.Center
    ) {
        MainContent(
            videoList = movieList,
            isRefreshing = true,
            onFavouriteClick = { movie -> viewModel.onFavouriteClick(movie) },
            onShareClick = { movie -> context.shareLink(movie.originalTitle) }
        )

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}