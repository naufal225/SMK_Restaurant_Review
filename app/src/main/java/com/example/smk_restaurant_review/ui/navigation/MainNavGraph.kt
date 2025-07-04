package com.example.smk_restaurant_review.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.smk_restaurant_review.ui.screens.main.DetailMenuScreen
import com.example.smk_restaurant_review.ui.screens.main.MenuScreen
import com.example.smk_restaurant_review.ui.viewmodels.MenuViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun NavGraphBuilder.mainNavGraph(navController: NavController, menuViewModel: MenuViewModel, modifier: Modifier = Modifier) {
    navigation(
        startDestination = Screen.Menu.route,
        route = Screen.Main.route
    ) {
        composable(Screen.Menu.route) {
            MenuScreen(navController, menuViewModel, modifier)
        }

        composable(Screen.Profile.route) {
        }

        composable(Screen.DetailMenu.route, arguments = listOf(navArgument("id") {type = NavType.IntType})) { backStack ->
            val id = backStack?.arguments?.getInt("id") ?: 0
            DetailMenuScreen(id, navController, menuViewModel,modifier)
        }

    }
}