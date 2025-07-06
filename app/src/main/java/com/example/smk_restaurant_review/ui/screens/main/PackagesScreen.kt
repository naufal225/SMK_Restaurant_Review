package com.example.smk_restaurant_review.ui.screens.main

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.ui.components.PackageCard
import com.example.smk_restaurant_review.ui.navigation.Screen
import com.example.smk_restaurant_review.ui.viewmodels.PackageViewModel
import com.google.gson.Gson

@Composable
fun PackagesScreen(navController: NavController, packageViewModel: PackageViewModel, modifier: Modifier){
    val packages by packageViewModel.packages.observeAsState()

    LaunchedEffect(Unit) {
        packageViewModel.getAllPackages()
    }

    Column(
        modifier = modifier.fillMaxWidth().padding(12.dp),
    ) {
        when(val result = packages) {
            is NetworkResponse.SUCCESS -> {
                LazyColumn {
                        items(result.data) {
                            val pkg = it
                            val json = Uri.encode(Gson().toJson(it))
                            PackageCard(pkg = pkg, onClick = {
                                navController.navigate(Screen.PackageDetail.passData(json
                                ))
                            })
                        }

                }
            }

            is NetworkResponse.ERROR -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = "Error",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            NetworkResponse.LOADING -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = "Data tidak ditemukan",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
