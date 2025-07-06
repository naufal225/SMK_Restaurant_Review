package com.example.smk_restaurant_review.ui.navigation

sealed class Screen(val route: String, val title: String? = null) {
    data object Auth : Screen("auth")

    data object Login : Screen("login", "Login")

    data object Main : Screen("main")
    data object Profile : Screen("profile", "Profile")

    data object Menu : Screen("menu", "Menu")
    data object DetailMenu : Screen("menu/{id}", "Detail Menu") {
        fun passId(id: Int) = "menu/${id}"
    }
    data object ReviewMenu : Screen("review/{id}", "Review Menu") {
        fun passId(id: Int) = "review/${id}"
    }

    data object History : Screen("history", "History")

    data object HistoryDetail : Screen("historyDetail/{data}", "Order History Detail") {
        fun passData(data: String) = "historyDetail/${data}"
    }

    data object Package : Screen("package", "Package")


}