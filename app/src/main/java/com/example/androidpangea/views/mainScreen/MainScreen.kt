package com.example.androidpangea.views.mainScreen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.maps.android.compose.GoogleMap

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold {
GoogleMap()
        Text(text = "Main")
    }
}