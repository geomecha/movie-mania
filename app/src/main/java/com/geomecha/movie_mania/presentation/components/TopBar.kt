package com.geomecha.movie_mania.presentation.components

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geomecha.movie_mania.R
import com.geomecha.movie_mania.presentation.theme.AppBarColor
import com.geomecha.movie_mania.presentation.theme.GREY

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home_title),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = GREY
                )
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.person_icon),
                    contentDescription = "Your Icon Description",
                    tint = GREY
                )
            }
        },
        colors = topAppBarColors(
            containerColor = AppBarColor,
            titleContentColor = GREY,
            actionIconContentColor = GREY
        ),
        modifier = Modifier
            .background(AppBarColor)
            .shadow(elevation = 3.dp, shape = RectangleShape)
    )
}