package com.example.smk_restaurant_review.ui.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.smk_restaurant_review.ui.components.BottomNavigationBar
import com.example.smk_restaurant_review.ui.navigation.RootNavGraph
import com.example.smk_restaurant_review.ui.navigation.Screen
import com.example.smk_restaurant_review.ui.viewmodels.AuthViewModel
import com.example.smk_restaurant_review.ui.viewmodels.MenuViewModel

@Composable
fun AppScaffold(navController: NavHostController, authViewModel: AuthViewModel, menuViewModel: MenuViewModel) {
    val listNavigation = listOf(
        Screen.Menu.route,
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showBottomNav = listNavigation.any {
        it == currentRoute
    }

    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                BottomNavigationBar(navController)
            }
        }
    ) { paddingValues ->
        RootNavGraph(navController, authViewModel, menuViewModel, Modifier.padding(paddingValues))
    }
}