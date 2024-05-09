package com.example.androidpangea.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidpangea.views.authentication.AuthScreen
import com.example.androidpangea.views.authentication.AuthViewModel
import com.example.androidpangea.views.authentication.SignInScreen
import com.example.androidpangea.views.authentication.SignUpScreen
import com.example.androidpangea.views.cameraScreen.CameraScreen
import com.example.androidpangea.views.mainScreen.MainScreen
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.mapScreen.MapViewModel
import com.example.androidpangea.views.postScreen.ExploreScreen
import com.example.androidpangea.views.userScreen.UserScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun AppNavHost(
    viewModel: AuthViewModel,
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),

    startDestination: String = NavigationItem.SignIn.route,
) {
    val auth: FirebaseAuth by lazy { Firebase.auth }
    val mapViewModel = MapViewModel()
    var isSplashScreenFinished by rememberSaveable {
        mutableStateOf(false)
    }




    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.Main.route) {
            MainScreen(viewModel,
//                navController = navController,
                state = mapViewModel.state.value,
                setupClusterManager = mapViewModel::setupClusterManager,
                calculateZoneViewCenter = mapViewModel::calculateZoneLatLngBounds,
            )
        }
//        composable(
//            "${NavigationItem.User.route}/{userid}",
//            arguments = listOf(
//                navArgument("userid") {
//                    type = NavType.StringType
//                }
//            )) {
//            val userId = it.arguments?.getString("userid")
//            UserScreen(
//                userId = userId.toString(),
//                navController = navController
//            )
//        }
        composable(NavigationItem.User.route) {
            UserScreen(navController = navController)

        }

        composable(NavigationItem.Camera.route) {
            CameraScreen(navController = navController)
        }
        composable(NavigationItem.Explore.route) {
            ExploreScreen(navController = navController)
        }
        composable(NavigationItem.SignUp.route) {
            SignUpScreen(
                //                onNavToHomePage = { navController.navigate(NavigationItem.Main.route) },
                //                onNavToLoginPage = { navController.navigate(NavigationItem.SignIn.route) },
                viewModel, navController
            )
        }

        composable(NavigationItem.SignIn.route) {
            SignInScreen(
                viewModel, navController
                //                onNavToHomePage = { navController.navigate(NavigationItem.Main.route) },
                //                onNavToSignUpPage = { navController.navigate(NavigationItem.SignUp.route) },

            )
        }
        composable(NavigationItem.Auth.route) {
            AuthScreen {

            }
        }


    }
}