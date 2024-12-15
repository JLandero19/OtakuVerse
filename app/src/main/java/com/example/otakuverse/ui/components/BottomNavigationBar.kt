package com.example.otakuverse.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.otakuverse.R

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?,
    sesion: Boolean
) {
    NavigationBar {
        val items = listOf(
            BottomNavItem("anime_list", Icons.AutoMirrored.Filled.List, stringResource(R.string.bottom_bar_anime)),
            BottomNavItem("fav_anime_list", Icons.Default.Favorite, stringResource(R.string.bottom_bar_favorite)),

            if (sesion) BottomNavItem("profile", Icons.Default.Person, stringResource(R.string.bottom_bar_profile))
            else BottomNavItem("login", Icons.Default.AccountCircle, stringResource(R.string.bottom_bar_login)),

            BottomNavItem("about", Icons.Default.Info, "Info")
        )
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

//Data class que define los items anteriores.
data class BottomNavItem(val route: String, val icon: ImageVector, val label: String)