package com.example.androidpangea.views.userScreen

import android.annotation.SuppressLint
import android.media.Image
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.androidpangea.models.User
import com.example.androidpangea.navigation.bar.BottomNavigationBar
import com.example.androidpangea.utils.StorageUtil
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.subviews.CircleImage
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
    val context = LocalContext.current
    var uri by remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text("Sign Out", fontSize = 16.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            CircleImage(modifier = Modifier.size(200.dp), url = uri.toString()) {

            }
            Button(onClick = {
                singlePhotoPicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )

            }) {
                Text("Edit Profile Picture")
            }

            Button(onClick = {
                uri?.let {
                    StorageUtil.uploadToStorage(uri = it, context = context, type = "image")
                }
            }) {
Text(text = "Save")
            }

            //            Image(painter = rememberAsyncImagePainter(model = getData.profileImage), contentDescription = null)

            //            Text(text = getData.email)
//            AsyncImage(model = uri, contentDescription = null, modifier = Modifier.size(200.dp))

        }
    }
}