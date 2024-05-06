package com.example.androidpangea.views.userScreen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.subviews.CircularImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserScreen(
//    userId: String,
    navController: NavController
) {


    Scaffold {
        CircularImage(imageUrl = "")
    }
}