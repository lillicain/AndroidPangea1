package com.example.androidpangea.views.cameraScreen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.navigation.bar.BottomNavigationBar
import com.example.androidpangea.views.subviews.ImagePicker
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Destination
@Composable
fun PhotoScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController) }) {

        ImagePicker()

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text("Back to Camera", fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(NavigationItem.Camera.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                        )
                    }
                }
            )
        }
    }
}