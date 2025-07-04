package com.example.smk_restaurant_review.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smk_restaurant_review.data.model.ListMenu
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.ui.components.ProductCard
import com.example.smk_restaurant_review.ui.viewmodels.MenuViewModel

@Composable
fun DetailMenuScreen(id : Int, navController: NavController, menuViewModel: MenuViewModel, modifier: Modifier) {
    val menuById by menuViewModel.menuById.observeAsState()

    val menuReviews

    LaunchedEffect(Unit) {
        menuViewModel.GetById(id)
    }

    Box(modifier = modifier.fillMaxSize()) {
        when(val result = menuById) {
            is NetworkResponse.SUCCESS -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(6.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(6.dp)
                        ) {
                            Text(
                                text = "Name: ${result.data.name}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(Modifier.height(12.dp))

                            Text(
                                text = "Price: Rp.${result.data.price}",
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(24.dp))


                }
            }

            is NetworkResponse.LOADING -> {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {}
        }

    }

}