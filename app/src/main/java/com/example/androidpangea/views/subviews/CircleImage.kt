package com.example.androidpangea.views.subviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.androidpangea.R

@Composable
fun CircleImage(
    modifier: Modifier,
    url: String,
    onClick: () -> Unit
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() },
        placeholder = if (MaterialTheme.colorScheme.primary == Color.DarkGray) {
            painterResource(id = R.drawable.light_image_place_holder)
        } else {
            painterResource(id = R.drawable.dark_image_place_holder)
        },
        contentScale = ContentScale.Crop,
    )
}