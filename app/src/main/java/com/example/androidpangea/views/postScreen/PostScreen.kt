package com.example.androidpangea.views.postScreen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.androidpangea.views.subviews.ImagePicker

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostScreen(navController: NavController) {
    Scaffold {
        ImagePicker()
    }
}