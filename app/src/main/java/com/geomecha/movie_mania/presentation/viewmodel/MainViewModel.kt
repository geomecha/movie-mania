package com.geomecha.movie_mania.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.geomecha.movie_mania.domain.usecase.AddToFavouriteUseCase
import com.geomecha.movie_mania.domain.usecase.GetFavouriteListUseCase
import com.geomecha.movie_mania.domain.usecase.GetMoviesListMaxVoteUseCase
import com.geomecha.movie_mania.domain.usecase.GetMoviesMaxCountVoteUseCase
import com.geomecha.movie_mania.domain.usecase.GetVideoListUseCase
import com.geomecha.movie_mania.domain.usecase.RemoveFromFavouriteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val getVideoListUseCase: GetVideoListUseCase,
    private val getFavouriteListUseCase: GetFavouriteListUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val getMoviesMaxCountVoteUseCase: GetMoviesMaxCountVoteUseCase,
    private val getMovieListMaxVoteUseCase: GetMoviesListMaxVoteUseCase
) : ViewModel() {

    private val _movieList = MutableStateFlow<PagingData<Any>>(PagingData.empty())
    val movieList: Flow<PagingData<Any>> = _movieList

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun onFavouriteClick(movie: Any): Boolean {
        return true
    }

}