package com.example.smk_restaurant_review.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smk_restaurant_review.data.model.LoginRequest
import com.example.smk_restaurant_review.data.model.LoginResponse
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.data.remote.SharedPrefsManager
import com.example.smk_restaurant_review.ui.navigation.Screen
import com.example.smk_restaurant_review.ui.viewmodels.AuthViewModel


@Composable
fun LoginScreen(modifier: Modifier = Modifier, authViewModel: AuthViewModel, navController: NavController) {
    val loginResult by authViewModel.login_result.observeAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    var checkedRemember by remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(loginResult) {
        when (loginResult) {
            is NetworkResponse.ERROR -> {
                authViewModel.login_result.postValue(null)
                Toast.makeText(
                    context,
                    (loginResult as NetworkResponse.ERROR).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            NetworkResponse.LOADING -> {}
            is NetworkResponse.SUCCESS -> {
                val result = (loginResult as NetworkResponse.SUCCESS<LoginResponse>).data
                val sharedPrefsManager = SharedPrefsManager(context)
                sharedPrefsManager.saveToken(result.token)
                authViewModel.login_result.postValue(null)
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                    launchSingleTop = true
                }
            }

            null -> {}
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(12.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SMK\nRESTAURANT",
                fontSize = 32.sp,
                fontWeight = Bold,
                color = Color.Black
            )
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Masukkan email anda") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Masukkan password anda") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility }
                ) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "passwordvisible"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedRemember,
                onCheckedChange = { checkedRemember = !checkedRemember }
            )

            Spacer(Modifier.width(6.dp))

            Text(
                text = "Keep me logged in",
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                authViewModel.Login(LoginRequest(email, password))
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Login",
                color = Color.White
            )
        }

    }

}
