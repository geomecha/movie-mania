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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
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

    private val _favouriteVideoList = MutableStateFlow<PagingData<Video>>(PagingData.empty())
    val favouriteVideoList: Flow<PagingData<Video>> = _favouriteVideoList

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _refreshTrigger: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 1)
    private val refreshTrigger: Flow<Unit> = _refreshTrigger

    init {
        getVideoList()
        getFavouriteList()
        launchRefreshTriger()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getVideoList() {
        viewModelScope.launch(Dispatchers.IO) {
            refreshTrigger
                .flatMapLatest {
                    getVideoListUseCase.invoke()
                        .distinctUntilChanged()
                        .cachedIn(viewModelScope)
                }
                .collect {
                    _videoList.value = it
                }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getFavouriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            refreshTrigger
                .flatMapLatest {
                    getFavouriteListUseCase.invoke()
                        .distinctUntilChanged()
                        .cachedIn(viewModelScope)
                }
                .collect {
                    _favouriteVideoList.value = it
                }
        }
    }

    private fun launchRefreshTriger() {
        viewModelScope.launch {
            _refreshTrigger.emit(Unit)
        }
    }

    fun onFavouriteClick(video: Video) {
        viewModelScope.launch(Dispatchers.IO) {
            if (video.isFavorite) {
                removeFromFavouriteUseCase.invoke(video)
            } else {
                addToFavouriteUseCase.invoke(video)
            }
            video.isFavorite = !video.isFavorite
            _refreshTrigger.emit(Unit)

        }

    }

}