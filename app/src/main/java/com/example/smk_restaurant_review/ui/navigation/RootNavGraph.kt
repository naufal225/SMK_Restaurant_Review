package com.example.smk_restaurant_review.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.smk_restaurant_review.ui.viewmodels.AuthViewModel
import com.example.smk_restaurant_review.ui.viewmodels.MenuViewModel


@Composable
fun RootNavGraph(navHostController: NavHostController, authViewModel: AuthViewModel, menuViewModel: MenuViewModel, modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Auth.route
    ) {
        authNavGraph(navController = navHostController, authViewModel = authViewModel, modifier = modifier)
        mainNavGraph(navHostController, menuViewModel, modifier = modifier)
    }
}