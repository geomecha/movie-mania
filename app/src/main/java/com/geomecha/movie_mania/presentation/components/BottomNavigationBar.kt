package com.geomecha.movie_mania.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.geomecha.movie_mania.R
import com.geomecha.movie_mania.presentation.theme.Orange
import com.geomecha.movie_mania.presentation.theme.TextColor
import com.geomecha.movie_mania.presentation.theme.BodySmallTextStyle

@Composable
fun BottomNavigationBar(
    navController: NavController = rememberNavController(),
    currentRoute: String?
) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = BottomNavigationDefaults.Elevation
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_home),
                    contentDescription = stringResource(id = R.string.home_title),
                    tint = if (isHomeItem(currentRoute)) Orange else TextColor
                )
            },
            label = {
                Text(
                    stringResource(id = R.string.home_title),
                    color = if (isHomeItem(currentRoute)) Orange else TextColor,
                    style = BodySmallTextStyle,
                )
            },
            selected = isHomeItem(currentRoute),
            onClick = { navController.navigate("home") },
            modifier = Modifier
                .padding(
                    top = 7.dp,
                    bottom = 5.dp
                )
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_heart),
                    contentDescription = stringResource(id = R.string.favourites_title),
                    tint = if (isHomeItem(currentRoute)) TextColor else Orange
                )
            },
            label = {
                Text(
                    stringResource(id = R.string.favourites_title),
                    color = if (isHomeItem(currentRoute)) TextColor else Orange,
                    style = BodySmallTextStyle,
                )
            },
            selected = currentRoute == stringResource(id = R.string.favourites_text),
            onClick = { navController.navigate("favourites") },
            modifier = Modifier
                .padding(
                    top = 7.dp,
                    bottom = 5.dp
                )
        )
    }
}

@Composable
private fun isHomeItem(currentRoute: String?) =
    currentRoute == stringResource(id = R.string.home_text)

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        navController = rememberNavController(),
        currentRoute = "home"
    )
}