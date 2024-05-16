package com.example.androidpangea.views.userScreen

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.androidpangea.models.User
import com.example.androidpangea.navigation.bar.BottomNavigationBar
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.subviews.CircularImage
import com.example.androidpangea.views.subviews.ImagePicker
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Destination
@Composable
fun UserScreen(
    id: String,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val getData = viewModel.state.value


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController) }) {
//        ImagePicker()


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {


            
            TopAppBar(
                title = {
                    Text("Sign Out", fontSize = 16.sp)
                },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back",)
                    }
                }
            )

            Image(painter = rememberAsyncImagePainter(model = getData.profileImage), contentDescription = null)

            Text(text = getData.email)

        }
    }
}