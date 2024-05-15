package com.example.androidpangea.views.cameraScreen

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.androidpangea.views.subviews.rotateBitmap
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import java.util.concurrent.Executor
import javax.inject.Inject


@Composable
fun LastPhotoPreview(
    modifier: Modifier = Modifier,
    lastCapturedPhoto: Bitmap
) {
    val capturedPhoto: ImageBitmap = remember(lastCapturedPhoto.hashCode()) { lastCapturedPhoto.asImageBitmap() }

    Card(
        modifier = modifier
            .size(128.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Image(
            bitmap = capturedPhoto,
            contentDescription = "Last captured photo",
            contentScale = ContentScale.Crop
        )
    }
}


interface PermissionListener {
    fun permissionGranted()
    fun permissionDenied()
}

abstract class AbstractPermissionObserver : LifecycleObserver {

    var granted: Boolean = false
    var permissionListener: PermissionListener? = null

    abstract fun launch()

    abstract fun checkPermission(): Boolean
}

@ActivityScoped
class CameraPermissionObserver @Inject constructor(
    @ActivityContext private val appCompatContext: Context
) : AbstractPermissionObserver() {

    private var requestPermission: ActivityResultLauncher<Array<String>>? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (appCompatContext is AppCompatActivity) {
            requestPermission = appCompatContext.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions.entries.forEach {
                    granted = it.value
                    if (!it.value) {
                        return@forEach
                    }
                }
                if (granted) {
                    permissionListener?.permissionGranted()
                } else {
                    permissionListener?.permissionDenied()
                }
            }
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        requestPermission = null
    }
    override fun launch() {
        requestPermission?.launch(arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE))
    }

    override fun checkPermission(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(appCompatContext, android.Manifest.permission.CAMERA)
        val writePermission = ContextCompat.checkSelfPermission(appCompatContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val readPermission = ContextCompat.checkSelfPermission(appCompatContext, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        return cameraPermission == PackageManager.PERMISSION_GRANTED &&
                writePermission == PackageManager.PERMISSION_GRANTED &&
                readPermission == PackageManager.PERMISSION_GRANTED
    }
}
fun capturePhoto(
    context: Context,
    cameraController: LifecycleCameraController,
    onPhotoCaptured: (Bitmap) -> Unit
) {
    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)

    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            val correctedBitmap: Bitmap = image
                .toBitmap()
                .rotateBitmap(image.imageInfo.rotationDegrees)

            onPhotoCaptured(correctedBitmap)
            image.close()
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("CameraContent", "Error capturing image", exception)
        }
    })
}
