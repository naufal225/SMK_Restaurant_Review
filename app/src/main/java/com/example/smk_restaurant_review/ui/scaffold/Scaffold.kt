package com.example.smk_restaurant_review.ui.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.smk_restaurant_review.ui.components.BottomNavigationBar
import com.example.smk_restaurant_review.ui.navigation.RootNavGraph
import com.example.smk_restaurant_review.ui.navigation.Screen
import com.example.smk_restaurant_review.ui.viewmodels.AuthViewModel
import com.example.smk_restaurant_review.ui.viewmodels.MenuViewModel
import com.example.smk_restaurant_review.ui.viewmodels.ReviewViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(navController: NavHostController, authViewModel: AuthViewModel, menuViewModel: MenuViewModel, reviewViewModel: ReviewViewModel) {
    val listNavigation = listOf(
        Screen.Menu.route,
        Screen.Profile.route,

    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showBottomNav = listNavigation.any {
        it == currentRoute
    }

    Scaffold(
        topBar = {
            if(currentRoute == Screen.Profile.route || currentRoute == Screen.Menu.route || currentRoute.toString().startsWith("menu") || currentRoute.toString().startsWith("review")) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(Color.White),
                    title = {
                        when(currentRoute) {
                            Screen.Menu.route -> Text(Screen.Menu.title.toString(), fontWeight = FontWeight.Bold)
                            Screen.Profile.route -> Text(Screen.Profile.title.toString(), fontWeight = FontWeight.Bold)
                            Screen.DetailMenu.route -> Text(Screen.DetailMenu.title.toString(), fontWeight = FontWeight.Bold)
                            Screen.ReviewMenu.route -> Text(Screen.ReviewMenu.title.toString(), fontWeight = FontWeight.Bold)

                        }
                    }
                )
            }

        },
        bottomBar = {
            if (showBottomNav) {
                BottomNavigationBar(navController)
            }
        }
    ) { paddingValues ->
        RootNavGraph(navController, authViewModel, menuViewModel, reviewViewModel, Modifier.padding(paddingValues))
    }
}