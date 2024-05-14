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
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val mapViewModel: MapViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private val cameraViewModel: CameraViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                mapViewModel.getDeviceLocation(fusedLocationProviderClient)
            }
        }

    private fun askPermissions() = when {
        ContextCompat.checkSelfPermission(
            this,
            ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED -> {
            mapViewModel.getDeviceLocation(fusedLocationProviderClient)
        }
        else -> {
            requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        askPermissions()

        if (!hasRequiredPermissions()){
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )
        }

        setContent {
            AndroidPangeaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding(),
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

                    //                    CameraScreen(viewModel = cameraViewModel)
                    AppNavHost(
                        navController = rememberNavController(),

                        modifier = Modifier
                    )

//                    val scope = rememberCoroutineScope()
//                    val scaffoldState = rememberBottomSheetScaffoldState()
//                    val controller = remember {
//                        LifecycleCameraController(applicationContext).apply {
//                            setEnabledUseCases(
//                                CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
//                            )
//                        }
//                    }
//                    val viewModel = viewModel<CameraViewModel>()
//                    val bitmaps by viewModel.bitmaps.collectAsState()
//
//                    BottomSheetScaffold(
//                        scaffoldState = scaffoldState,
//                        sheetPeekHeight = 0.dp,
//                        sheetContent = {
//                            PhotoBottomSheetContent(
//                                bitmaps = bitmaps, modifier = Modifier.fillMaxWidth()
//                            )
//                        }) { padding ->
//                        Box(
//                            modifier = Modifier.fillMaxSize().padding(padding)
//                        ) {
//                            CameraScreen(
//                                controller = controller, modifier = Modifier.fillMaxSize()
//                            )
//
//                            IconButton(
//                                onClick = {
//                                    controller.cameraSelector =
//                                        if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
//                                            CameraSelector.DEFAULT_FRONT_CAMERA
//                                        } else CameraSelector.DEFAULT_BACK_CAMERA
//                                }, modifier = Modifier.offset(16.dp, 16.dp)
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Cameraswitch,
//                                    contentDescription = "Switch camera"
//                                )
//                            }
//
//                            Row(
//                                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
//                                    .padding(16.dp), horizontalArrangement = Arrangement.SpaceAround
//                            ) {
//                                IconButton(onClick = {
//                                    scope.launch {
//                                        scaffoldState.bottomSheetState.expand()
//                                    }
//                                }) {
//                                    Icon(
//                                        imageVector = Icons.Default.Photo,
//                                        contentDescription = "Open gallery"
//                                    )
//                                }
//                                IconButton(onClick = {
//                                    takePhoto(
//                                        controller = controller,
//                                        onPhotoTaken = cameraViewModel::onTakePhoto
//                                    )
//                                }) {
//                                    Icon(
//                                        imageVector = Icons.Default.PhotoCamera,
//                                        contentDescription = "Take photo"
//                                    )
//                                }
//                            }
//                        }
//                    }

                }
            }
        }
    }

    private fun takePhoto(
        controller: LifecycleCameraController,
        onPhotoTaken: (Bitmap) -> Unit
    ) {
        controller.takePicture(
            ContextCompat.getMainExecutor(applicationContext),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    val matrix = Matrix().apply {
                        postRotate(image.imageInfo.rotationDegrees.toFloat())
                    }
                    val rotatedBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0,
                        0,
                        image.width,
                        image.height,
                        matrix,
                        true
                    )

                    onPhotoTaken(rotatedBitmap)
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e("Camera", "Couldn't take photo: ", exception)
                }
            }
        )
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