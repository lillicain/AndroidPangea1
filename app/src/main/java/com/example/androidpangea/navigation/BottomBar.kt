package com.example.androidpangea.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomBar(items: List<BottomNavigationItem>, currentNavIndex: Int, onClick: (Int) -> Unit) {
    BottomAppBar {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.tertiary
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = currentNavIndex == index,
                    onClick = { onClick(index) },
                    icon = {
                        Icon(
                            imageVector = if (currentNavIndex == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title
                        ) })
            }
        }
    }
}

data class BottomNavigationItem(
    var id: String,
var title: String,
var selectedIcon: ImageVector,
var unselectedIcon: ImageVector
)
