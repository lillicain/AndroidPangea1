package com.example.androidpangea.views.userScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.navigation.BottomNavigationBar
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.subviews.CircularImage
import com.example.androidpangea.views.subviews.ImagePicker

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserScreen(
//    userId: String,
//    navController: NavController
) {

val navController = rememberNavController()


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController) }) {
ImagePicker()

    }
}