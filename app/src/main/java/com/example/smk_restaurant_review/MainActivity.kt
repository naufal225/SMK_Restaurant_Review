package com.example.smk_restaurant_review

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.smk_restaurant_review.ui.scaffold.AppScaffold
import com.example.smk_restaurant_review.ui.theme.SMK_Restaurant_ReviewTheme
import com.example.smk_restaurant_review.ui.viewmodels.AuthViewModel
import com.example.smk_restaurant_review.ui.viewmodels.MenuViewModel
import com.example.smk_restaurant_review.ui.viewmodels.OrderViewModel
import com.example.smk_restaurant_review.ui.viewmodels.PackageViewModel
import com.example.smk_restaurant_review.ui.viewmodels.ReviewViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val authViewModel : AuthViewModel by viewModels()
        val menuViewModel : MenuViewModel by viewModels()
        val reviewViewModel : ReviewViewModel by viewModels()
        val orderViewModel : OrderViewModel by viewModels()
        val packageViewModel : PackageViewModel by viewModels()


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SMK_Restaurant_ReviewTheme {
                AppScaffold(navController, authViewModel, menuViewModel, reviewViewModel, orderViewModel, packageViewModel)
            }
        }
    }
}