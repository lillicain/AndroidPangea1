package com.example.androidpangea.views.userScreen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.androidpangea.views.subviews.CircularImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserScreen(
    userId: String,
    userViewModel: UserViewModel,
    navController: NavController,
) {
Scaffold {
    CircularImage(imageUrl = "")
}
}