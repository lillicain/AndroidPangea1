package com.example.androidpangea.data.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.androidpangea.views.home.HomeScreen
import com.example.androidpangea.views.HomeViewModel

@Composable
fun AppNavHost(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var isSplashScreenFinished by rememberSaveable {
        mutableStateOf(false)
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.HomeScreen.route

    ) {

        composable(NavigationItem.HomeScreen.route) {
            HomeScreen(homeViewModel = homeViewModel, navController = navController)
        }
        composable(NavigationItem.MapScreen.route) {

        }

        composable(NavigationItem.CameraScreen.route) {
//            CameraScreen()
        }

        composable(NavigationItem.ProfileScreen.route) {
            //          ProfileScreen(userId = , homeViewModel = , navController = )
        }

        composable(
            "${NavigationItem.ProfileScreen.route}/{userid}",
            arguments = listOf(
                navArgument("userid") {
                    type = NavType.StringType
                }
            )) {
            val userId = it.arguments?.getString("userid")
//            ProfileScreen(
//                userId = userId ?: "",
//                homeViewModel = homeViewModel,
//                navController = navController
//            )
        }
    }
}