package com.geomecha.movie_mania.presentation.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.geomecha.movie_mania.presentation.viewmodel.MainViewModel

@Composable
fun FavouritesScreen(navController: NavHostController, viewModel: MainViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Text(text = "some text")
            Button(onClick = { navController.navigate("details") }) {
                Text(text = "Go to Details")
            }
        }
    }
}