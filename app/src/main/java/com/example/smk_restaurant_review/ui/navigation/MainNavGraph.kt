package com.example.smk_restaurant_review.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.smk_restaurant_review.data.model.OrderHistoryItem
import com.example.smk_restaurant_review.data.model.Package
import com.example.smk_restaurant_review.ui.screens.main.DetailMenuScreen
import com.example.smk_restaurant_review.ui.screens.main.MenuScreen
import com.example.smk_restaurant_review.ui.screens.main.OrderDetailScreen
import com.example.smk_restaurant_review.ui.screens.main.OrderHistoryScreen
import com.example.smk_restaurant_review.ui.screens.main.PackageDetailScreen
import com.example.smk_restaurant_review.ui.screens.main.PackagesScreen
import com.example.smk_restaurant_review.ui.screens.main.ProfileScreen
import com.example.smk_restaurant_review.ui.screens.main.ReviewScreen
import com.example.smk_restaurant_review.ui.viewmodels.MenuViewModel
import com.example.smk_restaurant_review.ui.viewmodels.OrderViewModel
import com.example.smk_restaurant_review.ui.viewmodels.PackageViewModel
import com.example.smk_restaurant_review.ui.viewmodels.ReviewViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun NavGraphBuilder.mainNavGraph(navController: NavController, menuViewModel: MenuViewModel, reviewViewModel: ReviewViewModel, orderViewModel: OrderViewModel, packageViewModel: PackageViewModel, modifier: Modifier = Modifier) {
    navigation(
        startDestination = Screen.Menu.route,
        route = Screen.Main.route
    ) {
        composable(Screen.Menu.route) {
            MenuScreen(navController, menuViewModel, modifier)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController, modifier)
        }

        composable(Screen.History.route) {
            OrderHistoryScreen(navController, orderViewModel, modifier)
        }

        composable(Screen.HistoryDetail.route, listOf(navArgument("data") {type = NavType.StringType})) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("data")
            val order = Gson().fromJson(json, OrderHistoryItem::class.java)

            OrderDetailScreen(order, navController, modifier)
        }

        composable(Screen.Package.route) {
            PackagesScreen(navController, packageViewModel, modifier)
        }

        composable(Screen.PackageDetail.route, listOf(navArgument("data") {type = NavType.StringType})) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("data")
            val packages = Gson().fromJson(json, Package::class.java)

            PackageDetailScreen(packages, packageViewModel, navController, modifier)
        }

        composable(Screen.DetailMenu.route, arguments = listOf(navArgument("id") {type = NavType.IntType})) { backStack ->
            val id = backStack?.arguments?.getInt("id") ?: 0
            DetailMenuScreen(id, navController, menuViewModel, reviewViewModel,modifier)
        }

        composable(Screen.ReviewMenu.route, arguments = listOf(navArgument("id") {type = NavType.IntType})) { backStack ->
            val id = backStack?.arguments?.getInt("id") ?: 0
            ReviewScreen(id, navController, reviewViewModel,modifier)
        }

    }
}