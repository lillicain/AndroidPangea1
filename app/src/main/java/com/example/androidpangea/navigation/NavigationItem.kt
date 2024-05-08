package com.example.androidpangea.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.HMobiledata
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    MAIN,
    USER,
    MAP,
    CAMERA,
    EXPLORE,
    SIGNUP,
    SIGNIN,
AUTH
}

sealed class NavigationItem(val route: String, val icon: ImageVector?) {
    object Main : NavigationItem(Screen.MAIN.name, icon = Icons.Rounded.Home)
    object User : NavigationItem(Screen.USER.name, icon = Icons.Rounded.Home)
    object Map : NavigationItem(Screen.MAP.name, icon = Icons.Rounded.Home)
    object Camera: NavigationItem(Screen.CAMERA.name, icon = Icons.Rounded.Home)
    object Explore: NavigationItem(Screen.EXPLORE.name, icon = Icons.Rounded.Home)
    object SignIn: NavigationItem(Screen.SIGNIN.name, icon = Icons.Rounded.Home)
    object SignUp: NavigationItem(Screen.SIGNUP.name, icon = Icons.Rounded.Home)

    object Auth: NavigationItem(Screen.AUTH.name, icon = Icons.Rounded.Home)
}