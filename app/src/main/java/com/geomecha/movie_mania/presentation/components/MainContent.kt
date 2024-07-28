package com.geomecha.movie_mania.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.compose.LazyPagingItems
import com.geomecha.movie_mania.R
import com.geomecha.movie_mania.presentation.constants.EMPTY_SIZE
import com.geomecha.movie_mania.presentation.extensions.isLoading
import com.geomecha.movie_mania.presentation.theme.BackgroundColor
import com.geomecha.movie_mania.presentation.theme.Orange

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    movieList: LazyPagingItems<Any>,
    isRefreshing: Boolean,
    onShareClick: (Any) -> Unit,
    onFavouriteClick: (Any) -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(isRefreshing, { movieList.refresh() })

    Box(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Orange,
            strokeWidth = 4.dp,
            modifier = Modifier
                .size(36.dp)
                .zIndex(Z_INDEX_TOP)
                .alpha(
                    when (movieList.loadState.isLoading()) {
                        true -> ALPHA_VISIBLE
                        else -> ALPHA_INVISIBLE
                    }
                )
        )

        if (movieList.itemCount > EMPTY_SIZE && !movieList.loadState.isLoading()) {
            EmptyState(
                message = stringResource(id = R.string.no_movies_available),
                onRetry = { movieList.refresh() }
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 20.dp,
                    bottom = 12.dp
                ),
                state = rememberForeverLazyListState(key = SCROLL_KEY)
            ) {
                items(movieList.itemCount) {
                    movieList[it]?.let {
                        MovieCard(
                            movie = Any(),
                            onFavouriteClick = { movie ->
                                onFavouriteClick(movie)
                            },
                            onShareClick = { movie ->
                                onShareClick(movie)
                            }
                        )
                    }
                }
            }
        }
    }
}

private const val Z_INDEX_TOP = 2f
private const val SCROLL_KEY = "scrolling_position"
const val ALPHA_VISIBLE = 1f
const val ALPHA_INVISIBLE = 0f
