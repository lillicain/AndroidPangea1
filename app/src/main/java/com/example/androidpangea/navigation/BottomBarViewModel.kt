package com.example.androidpangea.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BottomBarViewModel: ViewModel() {
    var currentNavItemIndex by mutableStateOf(0)

//    var items = listOf(
//        BottomNavItem("", Icons.Filled.Face, Icons.Outlined.Face),
//        BottomNavItem("", Icons.Filled.Face, Icons.Outlined.Face),
//        BottomNavItem("", Icons.Filled.Face, Icons.Outlined.Face),
//    )
}