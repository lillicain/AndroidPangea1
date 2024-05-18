package com.example.androidpangea.views.userScreen.components

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.example.androidpangea.R

@Composable
fun ImagePicker(modifier: Modifier = Modifier, onImageSelected: (Uri) -> Unit = {}) {
    val context = LocalContext.current

    val launcherImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            val contentResolver = context.contentResolver
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION

            uri?.let {
                onImageSelected(it)
                contentResolver.takePersistableUriPermission(it, flag)
            }
        }
    )

    val launcherPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {

                launcherImage.launch(arrayOf("image/*"))
            } else {

            }
        }
    )

    IconButton(
        modifier = modifier,

        onClick = {
            checkAndRequestPermissions(
                launcher = launcherPermission,
                context = context,
                permissionGranted = {
                    launcherImage.launch(
                        arrayOf("image/*")
                    )
                },
            )
        },
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

fun checkAndRequestPermissions(
    launcher: ActivityResultLauncher<String>,
    context: Context,
    permissionGranted: () -> Unit
) {
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_IMAGES
        ) -> {
            permissionGranted()
        }

        else -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                launcher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }
}