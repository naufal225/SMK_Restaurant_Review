package com.example.smk_restaurant_review.ui.navigation

sealed class Screen(val route: String) {
    data object Auth : Screen("auth")

    data object Login : Screen("login")

    data object Main : Screen("main")
    data object Profile : Screen("profile")

    data object Menu : Screen("menu")
    data object DetailMenu : Screen("menu/{id}") {
        fun passId(id: Int) = "menu/${id}"
    }


}