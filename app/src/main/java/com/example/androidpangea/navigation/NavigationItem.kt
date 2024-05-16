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
    PHOTO,
    EXPLORE,
    SIGNUP,
    SIGNIN
}

sealed class NavigationItem(val route: String, val icon: ImageVector?) {
    object Main: NavigationItem(Screen.MAIN.name, icon = Icons.Filled.Map)
    object User: NavigationItem(Screen.USER.name, icon = Icons.Filled.Person)
    object Map: NavigationItem(Screen.MAP.name, icon = Icons.Filled.PinDrop)
    object Camera: NavigationItem(Screen.CAMERA.name, icon = Icons.Filled.Camera)
    object Photo: NavigationItem(Screen.PHOTO.name, icon = Icons.Filled.Camera)
    object Explore: NavigationItem(Screen.EXPLORE.name, icon = Icons.Filled.Search)
    object SignIn: NavigationItem(Screen.SIGNIN.name, icon = Icons.Rounded.Home)
    object SignUp: NavigationItem(Screen.SIGNUP.name, icon = Icons.Rounded.Home)
}