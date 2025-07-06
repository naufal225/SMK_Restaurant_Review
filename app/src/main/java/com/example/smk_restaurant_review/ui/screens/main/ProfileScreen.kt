package com.example.smk_restaurant_review.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smk_restaurant_review.data.remote.SharedPrefsManager
import com.example.smk_restaurant_review.ui.navigation.Screen


@Composable
fun ProfileScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val sharedPrefsManager = remember { SharedPrefsManager(context) }

    val name = sharedPrefsManager.getName() ?: "Tidak diketahui"
    val email = sharedPrefsManager.getEmail() ?: "Tidak diketahui"

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Profil",
            style = MaterialTheme.typography.headlineMedium
        )

        Divider()

        Text(text = "Nama: $name", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Email: $email", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                sharedPrefsManager.clear()

                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}