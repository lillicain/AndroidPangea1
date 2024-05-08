package com.example.androidpangea.navigation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Main,
        NavigationItem.User,
        NavigationItem.Camera,
        NavigationItem.Explore
    )
    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItem.Main.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon!!, contentDescription = item.route) },
                label = { Text(item.route) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
//@Composable
//fun BottomNavigationBar(
//    items: List<BottomNavItem>?,
//    navController: NavController,
//    modifier: Modifier = Modifier,
//    onItemClick: (BottomNavItem) -> Unit,
//) {
////    val items = List<BottomNavItem>()
//
//
////        NavigationItem.User,
////        NavigationItem.Main,
////        NavigationItem.Camera,
////        NavigationItem.Explore
////    )
//    val backStackEntry = navController.currentBackStackEntryAsState()
//    BottomNavigation(
//        modifier = modifier,
//        backgroundColor = MaterialTheme.colorScheme.background,
//    ) {
//        val inactiveColor = Color.Gray
//        items?.forEach { item ->
//            val selected = item.route == backStackEntry.value?.destination?.route
//
//            BottomNavigationItem(selected = selected, onClick = { onItemClick(item) }, icon = {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    if (item.badgeCount > 0) {
//                        BadgedBox(badge = {
//                            Text(text = item.badgeCount.toString())
//                        }) {
//                            Icon(
//                                painter = item.icon,
//                                contentDescription = item.name
//                            )
//                        }
//                    } else {
//                        Icon(
//                            painter = item.icon,
//                            contentDescription = item.name
//                        )
//                    }
//                }
//            })
//            BottomNavigationItem(
//                modifier = modifier.animateContentSize(
//                    animationSpec =  tween(5000)
//                ),
//                selected = selected,
//                onClick = { onItemClick(item) },
//                selectedContentColor = MaterialTheme.colorScheme.onBackground,
////                unselectedContentColor = inactiveColor,
//                icon = {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        if (item.badgeCount > 0) {
//                            BadgedBox(badge = {
//                                Text(text = item.badgeCount.toString())
//                            }) {
//                                Icon(
//                                    painter = item.icon,
//                                    contentDescription = item.name
//                                )
//                            }
//                        } else {
//                            Icon(
//                                painter = item.icon,
//                                contentDescription = item.name
//                            )
//                        }
//                    }
//                })
//        }
//    }
//}


@Composable
public fun RowScope.BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable() (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalContentColor.current,
): Unit {
}