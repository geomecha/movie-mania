package com.geomecha.movie_mania.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomOutlinedButton(
    modifier: Modifier,
    color: Color,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.White,
            contentColor = color
        ),
        border = BorderStroke(1.dp, color),
        onClick = onClick
    ) {
        Text(text = text)
    }
}