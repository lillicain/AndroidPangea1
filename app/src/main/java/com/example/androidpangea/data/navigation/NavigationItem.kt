package com.example.androidpangea.data.navigation

enum class Screen {
    HomeScreen,
    MapScreen,
    CameraScreen,
    ProfileScreen
}

sealed class NavigationItem(val route: String) {
    object HomeScreen : NavigationItem(Screen.HomeScreen.name)
    object MapScreen: NavigationItem(Screen.MapScreen.name)
    object CameraScreen : NavigationItem(Screen.CameraScreen.name)
    object ProfileScreen : NavigationItem(Screen.ProfileScreen.name)

}