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
        startDestination = if(isSplashScreenFinished){
            NavigationItem.Main.route
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
//        composable(NavigationItem.User.route) {
//            UserScreen(navController = navController)
//        }
        composable(NavigationItem.Camera.route) {
            CameraScreen(navController = navController)
        }
        composable(NavigationItem.Explore.route) {
            ExploreScreen(viewModel = viewModel, navController = navController)
        }


        //        composable("${NavigationItem.ViewPost.route}/{postId}",
        //            arguments = listOf(
        //                navArgument("postId") {
        //                    type = NavType.StringType
        //                }
        //            )) {
        //            val postId = it.arguments?.getString("postId")
        //            postId?.let { id ->
        //                ViewPostScreen(id, homeViewModel = homeViewModel, navController = navController)
        //            }
        //        }
        //        composable("${NavigationItem.ViewStory.route}/{storyId}/{userId}",
        //            arguments = listOf(
        //                navArgument("storyId") {
        //                    type = NavType.StringType
        //                }, navArgument("userId") {
        //                    type = NavType.StringType
        //                }
        //            )) {
        //            val storyId = it.arguments?.getString("storyId")
        //            val userId = it.arguments?.getString("userId")
        ////            if (storyId != null && userId != null) {
        ////                ViewStory(
        ////                    storyId,
        ////                    userId,
        ////                    homeViewModel = homeViewModel,
        ////                    navController = navController
        ////                )
        ////            }
        ////        }
        ////        composable(NavigationItem.Notification.route) {
        ////            NotificationScreen(homeViewModel = homeViewModel, navController = navController)
        ////        }
    }
}