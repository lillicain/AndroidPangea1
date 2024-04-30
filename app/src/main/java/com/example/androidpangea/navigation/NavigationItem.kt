package com.example.androidpangea.navigation

enum class Screen {
    SPLASH,
    HOME,
    PROFILE,
    CREATE_POST,
    SEARCH,
    VIEW_POST,

}

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Profile : NavigationItem(Screen.PROFILE.name)
    object CreatePost : NavigationItem(Screen.CREATE_POST.name)
    object Search : NavigationItem(Screen.SEARCH.name)
    object ViewPost : NavigationItem(Screen.VIEW_POST.name)

}