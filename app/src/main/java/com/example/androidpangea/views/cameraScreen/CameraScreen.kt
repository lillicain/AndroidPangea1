package com.example.androidpangea.views.cameraScreen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.navigation.bar.BottomNavigationBar
import com.example.androidpangea.utils.StorageUtil
import com.example.androidpangea.views.subviews.ImagePicker
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen(navController: NavController) {
//    val CAMERAX_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO)

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE)
        }
    }
    val viewModel = viewModel<CameraViewModel>()
    val bitmaps by viewModel.bitmaps.collectAsState()
//    var imageUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }

    var uri by remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickMultipleVisualMedia(),
//        onResult = { uri: List<Uri?> -> imageUris = uri }
//    )

    val location by remember { mutableStateOf(null) }


    fun takePhoto(controller: LifecycleCameraController, onPhotoTaken: (Bitmap) -> Unit) {
        controller.takePicture(ContextCompat.getMainExecutor(context),
            object: ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    val matrix = Matrix().apply {
                        postRotate(image.imageInfo.rotationDegrees.toFloat())
                    }
                    val rotatedBitmap = Bitmap.createBitmap(image.toBitmap(), 0, 0, image.width, image.height, matrix, true)
                    onPhotoTaken(rotatedBitmap)
                }
                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e("Camera", "Couldn't take photo: ", exception)
                }
            })
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController) }) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetContent = {

                AsyncImage(model = uri, contentDescription = null, modifier = Modifier.size(200.dp))

//                PhotoBottomSheetContent(bitmaps = bitmaps, modifier = Modifier.fillMaxWidth())


            }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CameraController(controller = controller, modifier = Modifier.fillMaxSize())

                IconButton(onClick = {
                        controller.cameraSelector = if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            } else {
                                CameraSelector.DEFAULT_BACK_CAMERA
                            }
                }) {
                    Icon(
                        imageVector = Icons.Default.Cameraswitch,
                        contentDescription = "Switch Camera"
                    )
                }
//                IconButton(
//                    onClick = {
//                        controller.cameraSelector =
//                            if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
//                                CameraSelector.DEFAULT_FRONT_CAMERA
//                            } else CameraSelector.DEFAULT_BACK_CAMERA
//                    }, modifier = Modifier.offset(10.dp, 10.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Cameraswitch,
//                        contentDescription = "Switch camera"
//                    )
//                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 100.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                   Button(onClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }, modifier = Modifier
//                        .padding(bottom = 48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Preview,
                            contentDescription = "Open gallery"
                        )
                    }

                   Button(onClick = {
                        takePhoto(
                            controller = controller, onPhotoTaken = viewModel::onTakePhoto
                        )

                    }, modifier = Modifier
//                        .padding(bottom = 48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            contentDescription = "Take photo"
                        )
                    }

//                    Button(onClick = {
//                        uri?.let {
//                            StorageUtil.uploadToStorage(uri = it, context = context, type = "image")
//                        }
//
//                    }) {
//                        Text("Upload")
//                    }

                   Button(onClick = {
                        singlePhotoPicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }, modifier = Modifier
//                        .padding(bottom = 48.dp)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Photo,
                            contentDescription = "Choose from photo library"
                        )
                    }
                }
                AsyncImage(model = uri, contentDescription = null, modifier = Modifier.size(200.dp))
            }
        }
    }
}
