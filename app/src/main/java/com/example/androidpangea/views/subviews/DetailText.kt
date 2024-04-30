package com.example.androidpangea.views.subviews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun DetailText(details: String, fontSize: Int = 24) {
    Text(text = details, fontSize = fontSize.sp)
}