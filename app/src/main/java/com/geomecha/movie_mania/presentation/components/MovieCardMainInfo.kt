package com.geomecha.movie_mania.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.geomecha.movie_mania.R
import com.geomecha.movie_mania.presentation.theme.GreyLight
import com.geomecha.movie_mania.presentation.theme.TextColor

@Preview
@Composable
fun MovieCardMainInfo(movie: Any = Any()) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(GreyLight),
                painter = rememberAsyncImagePainter(R.drawable.person_icon),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "4.5",//add rate
                fontSize = 12.sp,
                color = TextColor,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 20.dp)
        ) {
            Text(
                text = "Name",//add name
                fontSize = 18.sp,
                color = TextColor,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "description",
                fontSize = 12.sp,
                color = TextColor,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}