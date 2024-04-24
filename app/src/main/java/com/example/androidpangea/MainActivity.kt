package com.example.androidpangea

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.data.navigation.NavigationItem
import com.example.androidpangea.ui.theme.AndroidPangeaTheme
import com.example.androidpangea.views.HomeViewModel
import com.example.androidpangea.views.MyViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val homeViewModel: HomeViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPangeaTheme {


                val viewModel = hiltViewModel<MyViewModel>()


//                SetupTransparentSystemUi(actualBackgroundColor = MaterialTheme.colorScheme.surface, systemUiController = rememberSystemUiController())
//                Surface(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .statusBarsPadding()
//                        .navigationBarsPadding(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//
//                    val navController = rememberNavController()
//                    val screens = listOf(
//                        NavigationItem.HomeScreen.route,
//                        NavigationItem.MapScreen.route,
//                        NavigationItem.CameraScreen.route,
//                        NavigationItem.ProfileScreen.route,
//                        "${NavigationItem.ProfileScreen.route}/{userid}"
//                    )
//
//                    val showBottomBar = navController.currentBackStackEntryAsState().value?.destination?.route in screens.map { it }
//
//                    Scaffold {
//
////                        AppNavHost(homeViewModel = homeViewModel, navController = navController)
//
//                        //                        bottomBar = {
//                        //                            AnimatedVisibility(
//                        //                                visible = showBottomBar,
//                        //                                enter = fadeIn() + scaleIn(),
//                        //                                exit = fadeOut() + scaleOut(),
//                        //                            ) {
//                        //                                Row(
//                        //                                    horizontalArrangement = Arrangement.SpaceEvenly,
//                        //                                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
//                        //                                        .fillMaxWidth()
//                        //                                ) {
//                        //                                    BottomNavigationBar(items = listOf(
//                        //                                        BottomNavItem(
//                        //                                            NavigationItem.HomeScreen.route,
//                        //                                            Screen.HomeScreen.name,
//                        //                                            icon = rememberVectorPainter(image = Icons.Default.Home)
//                        //                                        ),
//                        //                                        BottomNavItem(
//                        //                                            NavigationItem.MapScreen.route,
//                        //                                            Screen.MapScreen.name,
//                        //                                            icon = rememberVectorPainter(image = Icons.Default.Search)
//                        //                                        ),
//                        //                                        BottomNavItem(
//                        //                                            NavigationItem.CameraScreen.route,
//                        //                                            Screen.CameraScreen.name,
//                        //                                            icon = rememberVectorPainter(image = Icons.Default.AddCircle)
//                        //                                        ),
//                        //                                        BottomNavItem(
//                        //                                            NavigationItem.ProfileScreen.route,
//                        //                                            Screen.ProfileScreen.name,
//                        //                                            icon = rememberVectorPainter(image = Icons.Default.Person)
//                        //                                        ),
//                        //                                        BottomNavItem(
//                        //                                            NavigationItem.ProfileScreen.route,
//                        //                                            Screen.ProfileScreen.name,
//                        //                                            icon = rememberVectorPainter(image = Icons.Default.Person)
//                        //                                        ),
//                        //                                    ), navController = navController, onItemClick = {
//                        //                                        if (it.route == NavigationItem.ProfileScreen.route) {
//                        //                                            navController.navigate(
//                        //                                                "${NavigationItem.ProfileScreen.route}/"
//                        //                                            )
//                        //                                        } else {
//                        //                                            navController.navigate(it.route)
//                        //                                        }
//                        //                                    })
//                        //                                }
//                        //                            }
//                    }
//                }
            }
        }
    }
}


@Composable
internal fun SetupTransparentSystemUi(
    systemUiController: SystemUiController = rememberSystemUiController(),
    actualBackgroundColor: Color,
) {
    val minLuminanceForDarkIcons = .5f

    SideEffect {
        systemUiController.setStatusBarColor(
            color = actualBackgroundColor,
            darkIcons = actualBackgroundColor.luminance() > minLuminanceForDarkIcons
        )
        systemUiController.setNavigationBarColor(
            color = actualBackgroundColor,
            darkIcons = actualBackgroundColor.luminance() > minLuminanceForDarkIcons,
            navigationBarContrastEnforced = false
        )
    }
}
