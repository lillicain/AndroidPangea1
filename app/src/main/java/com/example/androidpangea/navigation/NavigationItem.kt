package com.example.androidpangea.navigation

enum class Screen {
//    HOME,
//    PROFILE,
//    CREATE_POST,
//    SEARCH,
//    VIEW_POST,

    MAIN,
    USER,
    MAP,
    CAMERA,
    EXPLORE,

    SIGNIN,
    SIGNUP,

}

sealed class NavigationItem(val route: String) {


    object Main : NavigationItem(Screen.MAIN.name)
    object User : NavigationItem(Screen.USER.name)
    object Map : NavigationItem(Screen.MAP.name)
    object Camera: NavigationItem(Screen.CAMERA.name)
    object Explore: NavigationItem(Screen.EXPLORE.name)

    object SignIn: NavigationItem(Screen.SIGNIN.name)
    object SignUp: NavigationItem(Screen.SIGNUP.name)

//    object Home : NavigationItem(Screen.HOME.name)
//    object Profile : NavigationItem(Screen.PROFILE.name)
//    object CreatePost : NavigationItem(Screen.CREATE_POST.name)
//    object Search : NavigationItem(Screen.SEARCH.name)
//    object ViewPost : NavigationItem(Screen.VIEW_POST.name)

}