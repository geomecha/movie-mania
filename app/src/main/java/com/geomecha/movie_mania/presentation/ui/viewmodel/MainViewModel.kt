package com.geomecha.movie_mania.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.geomecha.movie_mania.domain.model.Video
import com.geomecha.movie_mania.domain.usecase.AddToFavouriteUseCase
import com.geomecha.movie_mania.domain.usecase.GetFavouriteListUseCase
import com.geomecha.movie_mania.domain.usecase.GetMoviesListMaxVoteUseCase
import com.geomecha.movie_mania.domain.usecase.GetMoviesMaxCountVoteUseCase
import com.geomecha.movie_mania.domain.usecase.GetVideoListUseCase
import com.geomecha.movie_mania.domain.usecase.RemoveFromFavouriteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainViewModel(
    private val getVideoListUseCase: GetVideoListUseCase,
    private val getFavouriteListUseCase: GetFavouriteListUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val getMoviesMaxCountVoteUseCase: GetMoviesMaxCountVoteUseCase,
    private val getMovieListMaxVoteUseCase: GetMoviesListMaxVoteUseCase
) : ViewModel() {

    private val _videoList = MutableStateFlow<PagingData<Video>>(PagingData.empty())
    val videoList: Flow<PagingData<Video>> = _videoList

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModelScope.launch {
            getVideoListUseCase.invoke()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _videoList.value = it
                }
        }
    }

    fun onFavouriteClick(movie: Any): Boolean {
        return true
    }

}