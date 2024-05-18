package com.example.androidpangea.views.userScreen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserUpdateFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    FloatingActionButton(
        modifier = modifier
            .padding(end = 24.dp, bottom = 24.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = null
        )
    }
}
