package com.example.smk_restaurant_review.ui.screens.main

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.ui.components.HistoryCard
import com.example.smk_restaurant_review.ui.navigation.Screen
import com.example.smk_restaurant_review.ui.viewmodels.OrderViewModel
import com.google.gson.Gson

@Composable
fun OrderHistoryScreen(
    navController: NavController,
    orderViewModel: OrderViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val orders by orderViewModel.menus.observeAsState()

    LaunchedEffect(Unit) {
        orderViewModel.getAllOrdersHistory()
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Riwayat Pesanan",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        when (val result = orders) {
            is NetworkResponse.LOADING -> {
                CircularProgressIndicator()
            }

            is NetworkResponse.SUCCESS -> {
                LazyColumn {
                    items(result.data) { order ->
                        HistoryCard(
                            order = order,
                            onClick = {
                                val json = Uri.encode(Gson().toJson(order))

                                navController.navigate(Screen.HistoryDetail.passData(
                                    json
                                ))
                            }
                        )
                    }
                }
            }

            is NetworkResponse.ERROR -> {
                Text("Gagal memuat data: ${result.message}", color = Color.Red)
            }

            else -> {}
        }
    }
}
