package com.example.androidpangea.navigation

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidpangea.views.authentication.AuthScreen
import com.example.androidpangea.views.authentication.SignInScreen
import com.example.androidpangea.views.authentication.SignUpScreen
import com.example.androidpangea.views.cameraScreen.CameraContent
import com.example.androidpangea.views.cameraScreen.CameraPermissionScreen
import com.example.androidpangea.views.cameraScreen.CameraScreen
import com.example.androidpangea.views.cameraScreen.CameraViewModel
import com.example.androidpangea.views.cameraScreen.PhotoScreen
import com.example.androidpangea.views.firebaseScreen.FirebaseSignInScreen
import com.example.androidpangea.views.firebaseScreen.FirebaseSignUpScreen
import com.example.androidpangea.views.firebaseScreen.FirebaseViewModel
import com.example.androidpangea.views.mainScreen.MainScreen
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.mapScreen.MapViewModel
import com.example.androidpangea.views.postScreen.ExploreScreen
import com.example.androidpangea.views.subviews.SavePhotoToGalleryUseCase
import com.example.androidpangea.views.userScreen.UserScreen
import com.google.android.play.integrity.internal.c
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppNavHost(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val mapViewModel = MapViewModel()
    val splashScreen by rememberSaveable { mutableStateOf(false) }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (splashScreen) {
            NavigationItem.Main.route
        } else {
            NavigationItem.SignIn.route
        }
    ) {

        composable(NavigationItem.Main.route) {
            MainScreen(
                navController = navController,
                state = mapViewModel.state.value,
                setupClusterManager = mapViewModel::setupClusterManager,
                calculateZoneViewCenter = mapViewModel::calculateZoneLatLngBounds,
            )
        }

        composable(NavigationItem.User.route) {
            UserScreen(navController = navController)
        }

        composable(NavigationItem.Camera.route) {
            CameraPermissionScreen(navController = navController)
        }
        composable(NavigationItem.Explore.route) {
            ExploreScreen(navController = navController)
        }
        composable(NavigationItem.SignUp.route) {
            SignUpScreen(navController = navController)


        }

        composable(NavigationItem.SignIn.route) {
            SignInScreen(navController = navController)
        }

        composable(NavigationItem.FirebaseSignIn.route) {
            FirebaseSignInScreen(navController = navController)
        }

        composable(NavigationItem.FirebaseSignUp.route) {
            FirebaseSignUpScreen(navController = navController)
        }
        composable(NavigationItem.Photo.route) {
            PhotoScreen(navController = navController)
        }
    }
}