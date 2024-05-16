package com.example.androidpangea.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.views.authentication.SignInScreen
import com.example.androidpangea.views.authentication.SignUpScreen
import com.example.androidpangea.views.cameraScreen.CameraPermissionScreen
import com.example.androidpangea.views.cameraScreen.PhotoScreen
import com.example.androidpangea.views.mainScreen.MainScreen
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.mapScreen.MapViewModel
import com.example.androidpangea.views.postScreen.ExploreScreen
import com.example.androidpangea.views.userScreen.UserScreen
import com.example.androidpangea.views.userScreen.UserViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppNavHost(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val mapViewModel = MapViewModel()
    val userViewModel = UserViewModel()
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
//        composable(
//        "${NavigationItem.User.route}/{id}",
//        arguments = listOf(
//            navArgument("id") {
//                type = NavType.StringType
//            }
//        )) {
//        val id = it.arguments?.getString("id")
//        UserScreen(
//            id = id ?: "",
//            navController = navController
//        )
//    }
//
//        composable("${NavigationItem.Explore.route}/{id}",
//            arguments = listOf(
//                navArgument("id") {
//                    type = NavType.StringType
//                }
//            )) {
//            val id = it.arguments?.getString("id")
//            id?.let { id ->
//                ExploreScreen(navController = navController)
//            }
//        }
        composable(NavigationItem.User.route) {
            UserScreen(id = this.toString(), navController = navController, viewModel = userViewModel)
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
        composable(NavigationItem.Photo.route) {
            PhotoScreen(navController = navController)
        }
    }
}