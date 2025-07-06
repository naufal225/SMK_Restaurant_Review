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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smk_restaurant_review.data.model.ListMenu
import com.example.smk_restaurant_review.data.model.Menu
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.ui.components.ProductCard
import com.example.smk_restaurant_review.ui.viewmodels.MenuViewModel


@Composable
fun MenuScreen(navController: NavController, menuViewModel: MenuViewModel, modifier: Modifier) {
    val menus by menuViewModel.menus.observeAsState()

    LaunchedEffect(Unit) {
        menuViewModel.GetAll()
    }

    var queryBarang by remember { mutableStateOf("") }
    val filteredMenus = remember(menus, queryBarang) {
        when(menus) {
            is NetworkResponse.SUCCESS -> {
                val data : List<Menu> = (menus as NetworkResponse.SUCCESS<ListMenu>).data
                data.filter {
                    it.name.contains(queryBarang, ignoreCase = true)
                }
            }
            else -> {
                emptyList<Menu>()
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth().padding(12.dp)
    ) {
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = queryBarang,
            onValueChange =  {queryBarang = it},
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    tint = Color.Gray
                )
            },
            placeholder = { Text("Cari items") }
        )

        when (val result = menus) {
            is NetworkResponse.LOADING -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is NetworkResponse.SUCCESS -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredMenus) {
                        ProductCard(
                            menu = it,
                            navController
                        )
                    }
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
