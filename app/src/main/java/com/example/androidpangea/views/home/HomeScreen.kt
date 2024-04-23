package com.example.androidpangea.views.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.androidpangea.views.HomeViewModel
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    //    modifier: Modifier,
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    val cameraPositionState = rememberCameraPositionState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        GoogleMap(
//            modifier = Modifier.fillMaxSize(),
//            cameraPositionState = cameraPositionState
//        )
    }
}