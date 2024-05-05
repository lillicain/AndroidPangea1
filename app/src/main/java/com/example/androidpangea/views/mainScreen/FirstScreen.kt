package com.example.androidpangea.views.mainScreen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.views.authentication.AuthViewModel
import com.example.androidpangea.views.authentication.SignInScreen
import com.example.androidpangea.views.authentication.SignUpScreen
import com.example.androidpangea.views.cameraScreen.CameraScreen
import com.example.androidpangea.views.mapScreen.MapViewModel
import com.example.androidpangea.views.userScreen.UserScreen
import com.example.androidpangea.views.userScreen.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstScreen(navController: NavHostController = rememberNavController(), authViewModel: AuthViewModel) {

    Scaffold {

Text("Pangea")

        NavHost(navController = navController, startDestination = LoginRoutes.SignIn.name) {
            composable(LoginRoutes.SignIn.name) {
                SignInScreen(onNavToHomePage = {
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
                SignUpScreen(onNavToHomePage = {
                    navController.navigate(HomeRoutes.Main.name) {
                        popUpTo(LoginRoutes.SignUp.name) {
                            inclusive = true
                        }
                    }
                }, loginViewModel = authViewModel) {
                    navController.navigate(LoginRoutes.SignIn.name)
                }
            }
            composable(HomeRoutes.Main.name) {

                UserScreen(
                    userViewModel = UserViewModel(),
                    navController = navController.navigate(HomeRoutes.Main.name)
                )
                navController.navigate(HomeRoutes.Main.name) {
                    popUpTo(HomeRoutes.Main.name) {
                        inclusive = true
                    }
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

}
