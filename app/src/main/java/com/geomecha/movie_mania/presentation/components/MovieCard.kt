package com.geomecha.movie_mania.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.geomecha.movie_mania.R
import com.geomecha.movie_mania.presentation.theme.ButtonColor
import com.geomecha.movie_mania.presentation.theme.SemiTransparentBlack

@Composable
fun MovieCard(
    movie: Any,
    onFavouriteClick: (Any) -> Unit,
    onShareClick: (Any) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, start = 4.dp, end = 4.dp, bottom = 12.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(CORNER_MAIN.dp),
                spotColor = SemiTransparentBlack,
                ambientColor = SemiTransparentBlack
            )
            .clip(RoundedCornerShape(CORNER_MAIN.dp))
            .clickable {
                onFavouriteClick(movie)
            }
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Box(Modifier.alpha(ALPHA_VISIBLE)) {
                Column {
                    MovieCardMainInfo(movie)

                    Spacer(Modifier.height(8.dp))
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = { onFavouriteClick(movie) }
                ) {
                    Text(
                        text = stringResource(id = R.string.like),
                        color = ButtonColor,
                        fontWeight = FontWeight.SemiBold
                    )

                }
                TextButton(
                    onClick = { onShareClick(movie) }
                ) {
                    Text(
                        text = stringResource(id = R.string.share),
                        color = ButtonColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

    }
}

private const val CORNER_MAIN = 8