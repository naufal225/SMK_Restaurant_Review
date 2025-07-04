package com.example.smk_restaurant_review.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.smk_restaurant_review.ui.screens.auth.LoginScreen
import com.example.smk_restaurant_review.ui.viewmodels.AuthViewModel


fun NavGraphBuilder.authNavGraph(navController: NavController, authViewModel: AuthViewModel, modifier: Modifier = Modifier) {
    navigation(
        startDestination = Screen.Login.route,
        route = Screen.Auth.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(modifier, authViewModel, navController)
        }
    }
}