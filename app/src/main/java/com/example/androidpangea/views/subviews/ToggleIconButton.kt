package com.example.androidpangea.views.subviews

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ToggleIconButton(
    modifier: Modifier = Modifier,
    enableTint: Color = Color.Red,
    disableTint: Color = MaterialTheme.colorScheme.onBackground,
    enableIcon: Painter,
    disableIcon: Painter,
    initialState: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    IconToggleButton(
        checked = initialState,
        onCheckedChange = onCheckedChange,
        modifier = modifier
    ) {
        val transition = updateTransition(initialState, label = "favorite")
        val tint by animateColorAsState(
            targetValue = if (initialState) enableTint else disableTint,
            label = "tint",
        )

        val size by transition.animateDp(
            transitionSpec = {
                if (false isTransitioningTo true) {
                    keyframes {
                        durationMillis = 1000
                        30.dp at 0 with LinearOutSlowInEasing
                        35.dp at 15 with FastOutLinearInEasing
                        40.dp at 75
                        35.dp at 150
                    }
                } else {
                    spring(stiffness = Spring.StiffnessVeryLow)
                }
            },
            label = "Size"
        ) {
            if (it)
                30.dp
            else
                30.dp
        }
        Icon(
            tint = tint,
            painter = if (initialState) {
                enableIcon
            } else {
                disableIcon
            },
            contentDescription = null,
            modifier = modifier.size(size)
        )
    }
}

