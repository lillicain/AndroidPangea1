package com.example.androidpangea.views.userScreen.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import com.example.androidpangea.R
import com.example.androidpangea.models.User
import com.example.androidpangea.views.mainScreen.MainViewModel

@Composable
fun User(
    user: User? = null,
    viewModel: MainViewModel = hiltViewModel(),
    navigateToSignInScreen: () -> Unit = {},
    navigateToForgotPasswordScreen: (() -> Unit)? = null,
    navigateToCountryDetailScreen: (countryCode: String, countryName: String) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = user?.profileImage, error = rememberVectorPainter(
            image = Icons.Default.AccountCircle
        ),
        onError = { error ->
            Log.e("Error", error.result.throwable.toString())
        }
    )
//    val location: Map<String, String>? by remember(user?.location) {
//        mutableStateOf(user?.location)
//    }


    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                Box {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.extraLarge)
                            .size(100.dp)
                    )
                    ImagePicker(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        onImageSelected = {
                            viewModel.updateProfilePhoto(it)
                        },
                    )
                }


                SettingsMenu(
                    modifier = Modifier.align(Alignment.TopEnd),
                    signOut = {
                        navigateToSignInScreen()
                        viewModel.signOut()
                    },
                    revokeAccess = {
                        navigateToSignInScreen()
                        viewModel.revokeAccess()
                    },
                    navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Fields(user = user)

        }
    }
}