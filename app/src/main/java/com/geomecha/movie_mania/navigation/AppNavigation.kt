package com.geomecha.movie_mania.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geomecha.movie_mania.presentation.ui.favourites.FavouritesScreen
import com.geomecha.movie_mania.presentation.ui.home.HomeScreen
import com.geomecha.movie_mania.presentation.ui.viewmodel.MainViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {

    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen(navController, viewModel) }
        composable("favourites") { FavouritesScreen(navController, viewModel) }
    }
}