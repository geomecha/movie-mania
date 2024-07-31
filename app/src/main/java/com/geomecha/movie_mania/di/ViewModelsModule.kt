package com.geomecha.movie_mania.di

import com.geomecha.movie_mania.presentation.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MainViewModel(get(), get(), get(), get(), get(), get(), get()) }
}