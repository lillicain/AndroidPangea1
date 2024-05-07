package com.example.androidpangea.navigation

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

sealed class NavigationItem(val route: String) {
    object Main : NavigationItem(Screen.MAIN.name)
    object User : NavigationItem(Screen.USER.name)
    object Map : NavigationItem(Screen.MAP.name)
    object Camera: NavigationItem(Screen.CAMERA.name)
    object Explore: NavigationItem(Screen.EXPLORE.name)
    object SignIn: NavigationItem(Screen.SIGNIN.name)
    object SignUp: NavigationItem(Screen.SIGNUP.name)

    object Auth: NavigationItem(Screen.AUTH.name)
}