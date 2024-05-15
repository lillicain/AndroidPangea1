package com.example.androidpangea

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.impl.utils.MatrixExt.postRotate
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.navigation.AppNavHost
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.ui.theme.AndroidPangeaTheme
import com.example.androidpangea.views.NavGraphs
import com.example.androidpangea.views.authentication.AuthViewModel
import com.example.androidpangea.views.cameraScreen.CameraContent
import com.example.androidpangea.views.cameraScreen.CameraScreen
import com.example.androidpangea.views.cameraScreen.CameraViewModel
import com.example.androidpangea.views.cameraScreen.PhotoBottomSheetContent
import com.example.androidpangea.views.firebaseScreen.FirebaseSignInScreen
import com.example.androidpangea.views.firebaseScreen.FirebaseSignUpScreen
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.mapScreen.MapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val mapViewModel: MapViewModel by viewModels()

    private lateinit var auth: FirebaseAuth

//    private val mainViewModel: MainViewModel by viewModels()
//    private val cameraViewModel: CameraViewModel by viewModels()
//    private val authViewModel: AuthViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean -> if (isGranted) { mapViewModel.getDeviceLocation(fusedLocationProviderClient) } }

    private fun askPermissions() = when {
        ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
            mapViewModel.getDeviceLocation(fusedLocationProviderClient)
        } else -> { requestPermissionLauncher.launch(ACCESS_FINE_LOCATION) }
    }


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        askPermissions()

        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(this, CAMERAX_PERMISSIONS, 0)
        }

//        auth = Firebase.auth

        setContent {
            AndroidPangeaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    //                    val navController = rememberNavController()
                    //                    val screens = listOf(
                    //                        NavigationItem.Main.route,
                    //                        NavigationItem.User.route,
                    //                        NavigationItem.Camera.route,
                    //                        NavigationItem.Explore.route,
                    ////                        "${NavigationItem.User.route}/{userid}",
                    ////                        NavigationItem.SignIn.route,
                    ////                        NavigationItem.SignUp.route
                    //                    )
                    //                    val showBottomBar = navController
                    //                        .currentBackStackEntryAsState().value?.destination?.route in screens.map { it }
                    //                    Scaffold(
                    //                        bottomBar = { BottomNavigationBar(navController = navController)} ) {


                    //                        AnimatedVisibility(
                    //                            visible = showBottomBar,
                    //                            enter = fadeIn() + scaleIn(),
                    //                            exit = fadeOut() + scaleOut(),
                    //                        ) {
                    //                            Row(
                    //                                horizontalArrangement = Arrangement.SpaceEvenly,
                    //                                modifier = Modifier
                    //                                    .background(MaterialTheme.colorScheme.background)
                    //                                    .fillMaxWidth()
                    //                            ) {
                    //                                BottomNavigationBar(navController = navController)
                    //                            }
                    //                                BottomNavigationBar(
                    //                                    items = listOf(
                    //                                    BottomNavItem(
                    //                                        NavigationItem.Main.route,
                    //                                        Screen.MAIN.name,
                    //                                        icon = rememberVectorPainter(image = Icons.Default.Home)
                    //                                    ),
                    //                                    BottomNavItem(
                    //                                        NavigationItem.User.route,
                    //                                        Screen.USER.name,
                    //                                        icon = rememberVectorPainter(image = Icons.Default.Search)
                    //                                    ),
                    //                                    BottomNavItem(
                    //                                        NavigationItem.Camera.route,
                    //                                        Screen.CAMERA.name,
                    //                                        icon = rememberVectorPainter(image = Icons.Default.AddCircle)
                    //                                    ),
                    //                                    BottomNavItem(
                    //                                        NavigationItem.Explore.route,
                    //                                        Screen.EXPLORE.name,
                    //                                        icon = rememberVectorPainter(image = Icons.Default.Explore)
                    //                                    ),
                    //                                    BottomNavItem(
                    //                                        NavigationItem.User.route,
                    //                                        Screen.USER.name,
                    //                                        icon = rememberVectorPainter(image = Icons.Default.Person)
                    //                                    ),
                    //                                ), navController = navController
                    //                                ) {
                    //                                    if (it.route == NavigationItem.User.route) {
                    //                                        navController.navigate(NavigationItem.User.route)
                    //                                    } else {
                    //                                        navController.navigate(it.route)
                    //                                    }
                    //                                }
                    //                            }
                    //                        }
                    ////                    }) {
                    //                    DestinationsNavHost(navGraph = NavGraphs.root)

                    AppNavHost(
                        navController = rememberNavController(),
                        modifier = Modifier
                    )
                }
            }
        }
    }


    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO,
        )
    }
}