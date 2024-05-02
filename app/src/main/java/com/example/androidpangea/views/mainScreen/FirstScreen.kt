package com.example.androidpangea.views.mainScreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.views.authentication.AuthViewModel
import com.example.androidpangea.views.authentication.LoginScreen
import com.example.androidpangea.views.authentication.SignUpScreen
import com.example.androidpangea.views.mapScreen.MapViewModel

@Composable
fun FirstScreen(navController: NavHostController = rememberNavController(), authViewModel: AuthViewModel, viewModel: MapViewModel) {
    NavHost(navController = navController, startDestination = LoginRoutes.SignIn.name) {
        composable(LoginRoutes.SignIn.name) {
            LoginScreen(onNavToHomePage = {
                navController.navigate(HomeRoutes.Main.name) {
                    launchSingleTop = true
                    popUpTo(LoginRoutes.SignIn.name) {
                        inclusive = true
                    }
                }
            }, loginViewModel = authViewModel) {
                navController.navigate(LoginRoutes.SignUp.name) {
                    launchSingleTop = true
                    popUpTo(LoginRoutes.SignIn.name) {
                        inclusive = true
                    }
                }
            }
        }
        composable(LoginRoutes.SignUp.name) {
            SignUpScreen(onNavToHomePage = {   navController.navigate(HomeRoutes.Main.name) {
                popUpTo(LoginRoutes.SignUp.name){
                    inclusive = true
                }
            }
            }, loginViewModel = authViewModel) {
                navController.navigate(LoginRoutes.SignIn.name)
            }
        }
        composable(HomeRoutes.Main.name) {

            MainScreen(state = viewModel.state.value, setupClusterManager = viewModel::setupClusterManager, calculateZoneViewCenter = viewModel::calculateZoneLatLngBounds)
            navController.navigate(HomeRoutes.Main.name) {
                popUpTo(HomeRoutes.Main.name) {
                    inclusive = true
                }
            }
        }
    }
}

enum class LoginRoutes {
    SignUp,
    SignIn
}
enum class HomeRoutes {
    Main,
    Detail
}
