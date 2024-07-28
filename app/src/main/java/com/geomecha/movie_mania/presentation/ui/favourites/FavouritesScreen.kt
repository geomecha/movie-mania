package com.geomecha.movie_mania.presentation.ui.favourites

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

    val movieList = viewModel.movieList.collectAsLazyPagingItems()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { movieList.refresh() })
    val context = LocalContext.current

    MainContent(
        movieList = movieList,
        isRefreshing = isRefreshing,
        onFavouriteClick = { movie -> viewModel.onFavouriteClick(movie) },
        onShareClick = { movie -> context.shareLink("") }
    )
}