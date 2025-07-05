package com.example.smk_restaurant_review.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.smk_restaurant_review.data.remote.SharedPrefsManager
import com.example.smk_restaurant_review.ui.viewmodels.AuthViewModel
import com.example.smk_restaurant_review.ui.viewmodels.MenuViewModel
import com.example.smk_restaurant_review.ui.viewmodels.ReviewViewModel


@Composable
fun RootNavGraph(navHostController: NavHostController, authViewModel: AuthViewModel, menuViewModel: MenuViewModel, reviewViewModel: ReviewViewModel, modifier: Modifier) {
    val context = LocalContext.current

    val sharedPrefsManager = SharedPrefsManager(context)
    NavHost(
        navController = navHostController,
        startDestination = Screen.Auth.route
//        startDestination = if(sharedPrefsManager.getToken() != null) Screen.Main.route else Screen.Auth.route
    ) {
        authNavGraph(navController = navHostController, authViewModel = authViewModel, modifier = modifier)
        mainNavGraph(navHostController, menuViewModel, reviewViewModel, modifier = modifier)
    }
}