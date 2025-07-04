package com.example.smk_restaurant_review.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.smk_restaurant_review.ui.navigation.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        Screen.Menu,
        Screen.Profile
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            navigationItems.forEach {
                IconButton(
                    onClick = {
                        navController.navigate(it.route) {
                            popUpTo(Screen.Menu.route) {inclusive = false}
                            launchSingleTop = true
                        }
                    }
                ) {
                    Icon(
                        imageVector = when (it) {
                            Screen.Menu -> Icons.Default.AddBox
                            Screen.Profile -> Icons.Default.Person
                            else -> Icons.Default.Help
                        },
                        tint = if(currentRoute == it.route) Color.Blue else Color.Gray,
                        contentDescription = it.route,
                    )
                }
            }
        }
    }
}