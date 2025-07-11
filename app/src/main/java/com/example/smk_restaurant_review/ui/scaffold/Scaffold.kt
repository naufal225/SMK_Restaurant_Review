package com.example.smk_restaurant_review.ui.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.smk_restaurant_review.ui.viewmodels.OrderViewModel
import com.example.smk_restaurant_review.ui.viewmodels.PackageViewModel
import com.example.smk_restaurant_review.ui.viewmodels.ReviewViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(navController: NavHostController, authViewModel: AuthViewModel, menuViewModel: MenuViewModel, reviewViewModel: ReviewViewModel, orderViewModel: OrderViewModel, packageViewModel: PackageViewModel) {
    val TopAppBarNav = listOf(
        Screen.Menu.route,
        Screen.Profile.route,
        Screen.Package.route,
        Screen.History.route,
        Screen.DetailMenu.route
    )

    val ButtomBarNav = listOf(
        Screen.Menu.route,
        Screen.Profile.route,
        Screen.Package.route,
        Screen.History.route,
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showBottomBar = ButtomBarNav.any {
        it == currentRoute
    }

    val showTopAppBar = TopAppBarNav.any {
        it == currentRoute
    }

    Scaffold(
        topBar = {
            if(showTopAppBar) {
                TopAppBar(
                    navigationIcon = {
                        val isHistoryDetail = currentRoute?.startsWith("historyDetail/") == true
                        val isDetailMenu = currentRoute == Screen.DetailMenu.route

                        if (isDetailMenu || isHistoryDetail) {

                            IconButton(
                                onClick = { navController.popBackStack() }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "back"
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(Color.White),
                    title = {
                        when(currentRoute) {
                            Screen.Menu.route -> Text(Screen.Menu.title.toString(), fontWeight = FontWeight.Bold)
                            Screen.Profile.route -> Text(Screen.Profile.title.toString(), fontWeight = FontWeight.Bold)
                            Screen.DetailMenu.route -> Text(Screen.DetailMenu.title.toString(), fontWeight = FontWeight.Bold)
                            Screen.ReviewMenu.route -> Text(Screen.ReviewMenu.title.toString(), fontWeight = FontWeight.Bold)
                            Screen.History.route -> Text(Screen.History.title.toString(), fontWeight = FontWeight.Bold)
                            Screen.Package.route -> Text(Screen.Package.title.toString(), fontWeight = FontWeight.Bold)

                        }
                    }
                )
            }

        },
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { paddingValues ->
        RootNavGraph(navController, authViewModel, menuViewModel, reviewViewModel, orderViewModel, packageViewModel, Modifier.padding(paddingValues))
    }
}