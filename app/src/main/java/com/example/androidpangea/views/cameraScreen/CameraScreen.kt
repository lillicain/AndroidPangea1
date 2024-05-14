package com.example.androidpangea.views.cameraScreen
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidpangea.views.subviews.rotateBitmap
import java.util.concurrent.Executor


@Composable
fun CameraScreen(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        factory = {
            PreviewView(it).apply {
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
            }
        },
        modifier = modifier
    )

//    viewModel: CameraViewModel //= koinViewModel()
//) {
//    val cameraState: CameraState by viewModel.state.collectAsStateWithLifecycle()
//
//    CameraContent(
//        onPhotoCaptured = viewModel::storePhotoInGallery,
//        lastCapturedPhoto = cameraState.capturedImage
//    )
}

@Composable
fun PhotoBottomSheetContent(
    bitmaps: List<Bitmap>,
    modifier: Modifier = Modifier
) {
    if(bitmaps.isEmpty()) {
        Box(
            modifier = modifier
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("There are no photos yet")
        }
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp,
            contentPadding = PaddingValues(16.dp),
            modifier = modifier
        ) {
            items(bitmaps) { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
    }
}

//@Composable
//private fun CameraContent(
//    onPhotoCaptured: (Bitmap) -> Unit,
//    lastCapturedPhoto: Bitmap? = null
//) {
//
//    val context: Context = LocalContext.current
//    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
//    val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                text = { Text(text = "Take photo") },
//                onClick = {
//                    capturePhoto(context, cameraController, onPhotoCaptured)
//
//                          },
//                icon = { Icon(imageVector = Icons.Default.Camera, contentDescription = "Camera capture icon") }
//            )
//        }
//    ) { paddingValues: PaddingValues ->
//
//        Box(modifier = Modifier.fillMaxSize()) {
//            AndroidView(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues),
//                factory = { context ->
//                    PreviewView(context).apply {
//                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
//                        setBackgroundColor(Color.BLACK)
//                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
//                        scaleType = PreviewView.ScaleType.FILL_START
//                    }.also { previewView ->
//                        previewView.controller = cameraController
//                        cameraController.bindToLifecycle(lifecycleOwner)
//                    }
//                }
//            )
//
//            if (lastCapturedPhoto != null) {
//                LastPhotoPreview(
//                    modifier = Modifier.align(alignment = BottomStart),
//                    lastCapturedPhoto = lastCapturedPhoto
//                )
//            }
//        }
//    }
//}

//private fun capturePhoto(
//    context: Context,
//    cameraController: LifecycleCameraController,
//    onPhotoCaptured: (Bitmap) -> Unit
//) {
//    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)
//
//    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
//        override fun onCaptureSuccess(image: ImageProxy) {
//            val correctedBitmap: Bitmap = image
//                .toBitmap()
//                .rotateBitmap(image.imageInfo.rotationDegrees)
//
//            onPhotoCaptured(correctedBitmap)
//            image.close()
//        }
//
//        override fun onError(exception: ImageCaptureException) {
//            Log.e("CameraContent", "Error capturing image", exception)
//        }
//    })
//}
//
//@Composable
//private fun LastPhotoPreview(
//    modifier: Modifier = Modifier,
//    lastCapturedPhoto: Bitmap
//) {
//
//    val capturedPhoto: ImageBitmap = remember(lastCapturedPhoto.hashCode()) { lastCapturedPhoto.asImageBitmap() }
//
//    Card(
//        modifier = modifier
//            .size(128.dp)
//            .padding(16.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        shape = MaterialTheme.shapes.large
//    ) {
//        Image(
//            bitmap = capturedPhoto,
//            contentDescription = "Last captured photo",
//            contentScale = androidx.compose.ui.layout.ContentScale.Crop
//        )
//    }
//}
//
//@Preview
//@Composable
//private fun Preview_CameraContent() {
//    CameraContent(
//        onPhotoCaptured = {}
//    )
//}
////@OptIn(ExperimentalPermissionsApi::class)
////@Destination
////@Composable
////fun CameraScreen(
////    viewModel: CameraViewModel = hiltViewModel(),
////    navController: NavController
////) {
////    val permissions = if (Build.VERSION.SDK_INT <= 28) {
////        listOf(
////            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
////        )
////    } else listOf(Manifest.permission.CAMERA)
////
////    val permissionState = rememberMultiplePermissionsState(
////        permissions = permissions
////    )
////
////
////    if (!permissionState.allPermissionsGranted) {
////        SideEffect {
////            permissionState.launchMultiplePermissionRequest()
////        }
////    }
////
////
////    val context = LocalContext.current
////    val lifecycleOwner = LocalLifecycleOwner.current
////    val configuration = LocalConfiguration.current
////    val screeHeight = configuration.screenHeightDp.dp
////    val screenWidth = configuration.screenWidthDp.dp
////    var previewView: PreviewView
////
////    val cameraState: CameraState by viewModel.state.collectAsStateWithLifecycle()
////
////    //    CameraContent(
////    //        onPhotoCaptured = viewModel::storePhotoInGallery,
////    //        lastCapturedPhoto = cameraState.capturedImage
////    //    )
////
////    Scaffold(
////        modifier = Modifier.fillMaxSize(),
////        bottomBar = { BottomNavigationBar(navController = navController) }) {
////        Column(
////            horizontalAlignment = Alignment.CenterHorizontally,
////            verticalArrangement = Arrangement.Center
////        ) {
////            if (permissionState.allPermissionsGranted) {
////                Box(
////                    modifier = Modifier.height(screeHeight * 0.85f).width(screenWidth)
////                ) {
////                    AndroidView(
////                        factory = {
////                            previewView = PreviewView(it)
////                            viewModel.showCameraPreview(previewView, lifecycleOwner)
////                            previewView
////                        }, modifier = Modifier.height(screeHeight * 0.85f).width(screenWidth)
////                    )
////                }
////            }
////
////            Box(
////                modifier = Modifier.height(screeHeight * 0.15f), contentAlignment = Alignment.Center
////            ) {
////                IconButton(onClick = {
////                    if (permissionState.allPermissionsGranted) {
////                        viewModel.captureAndSave(context)
////                    } else {
////                        Toast.makeText(
//                            context, "Please accept permission in app settings", Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }) {
//                    Icon(imageVector = Icons.Outlined.Camera, contentDescription = "")
//                    Icon(imageVector = Icons.Default.Image, contentDescription = "")
//
//
//                }
//            }
//        }
//    }
//}
