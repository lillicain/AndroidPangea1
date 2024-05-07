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
import androidx.navigation.navArgument
import com.example.androidpangea.views.authentication.SignInScreen
import com.example.androidpangea.views.authentication.SignUpScreen
import com.example.androidpangea.views.cameraScreen.CameraScreen
import com.example.androidpangea.views.mainScreen.MainScreen
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.mapScreen.MapViewModel
import com.example.androidpangea.views.postScreen.ExploreScreen
import com.example.androidpangea.views.userScreen.UserScreen

@Composable
fun AppNavHost(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val mapViewModel = MapViewModel()
    var isSplashScreenFinished by rememberSaveable {
        mutableStateOf(false)
    }



    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (viewModel.users != null) {
            NavigationItem.SignIn.route
        } else {
            NavigationItem.Main.route
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
        composable(
            "${NavigationItem.User.route}/{userid}",
            arguments = listOf(
                navArgument("userid") {
                    type = NavType.StringType
                }
            )) {
            val userId = it.arguments?.getString("userid")
            UserScreen(
                navController = navController
            )
        }
        composable(NavigationItem.User.route) {
            UserScreen(navController = navController)
        }

        composable(NavigationItem.Camera.route) {
            CameraScreen(navController = navController)
        }
        composable(NavigationItem.Explore.route) {
            ExploreScreen(viewModel = viewModel, navController = navController)
        }
        composable(NavigationItem.SignUp.route) {
            SignUpScreen(
                //                onNavToHomePage = { navController.navigate(NavigationItem.Main.route) },
                //                onNavToLoginPage = { navController.navigate(NavigationItem.SignIn.route) },
                navController = navController
            )
        }

        composable(NavigationItem.SignIn.route) {
            SignInScreen(
                //                onNavToHomePage = { navController.navigate(NavigationItem.Main.route) },
                //                onNavToSignUpPage = { navController.navigate(NavigationItem.SignUp.route) },
                navController = navController
            )
        }
    }
}