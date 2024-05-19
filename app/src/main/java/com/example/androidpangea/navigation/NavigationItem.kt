package com.example.androidpangea.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.HMobiledata
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

@Suppress("SpellCheckingInspection")
enum class Screen {
    MAIN,
    USER,
    MAP,
    CAMERA,
    EXPLORE,
    SIGNUP,
    SIGNIN
}

sealed class NavigationItem(val route: String, val icon: ImageVector?) {
    data object Main: NavigationItem(Screen.MAIN.name, icon = Icons.Filled.Map)
    data object User: NavigationItem(Screen.USER.name, icon = Icons.Filled.Person)
    data object Map: NavigationItem(Screen.MAP.name, icon = Icons.Filled.PinDrop)
    data object Camera: NavigationItem(Screen.CAMERA.name, icon = Icons.Filled.Camera)
    data object Explore: NavigationItem(Screen.EXPLORE.name, icon = Icons.Filled.Search)
    data object SignIn: NavigationItem(Screen.SIGNIN.name, icon = Icons.Rounded.Home)
    data object SignUp: NavigationItem(Screen.SIGNUP.name, icon = Icons.Rounded.Home)
}