package com.example.smk_restaurant_review.ui.screens.main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.smk_restaurant_review.data.model.Menu
import com.example.smk_restaurant_review.data.model.Package
import com.example.smk_restaurant_review.data.model.dto.OrderPackageRequest
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.data.remote.RetrofitInstance
import com.example.smk_restaurant_review.ui.components.ProductCard
import com.example.smk_restaurant_review.ui.viewmodels.PackageViewModel
@Composable
fun PackageDetailScreen(
    packageData: Package,
    packageViewModel: PackageViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var qty by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val orderResponse by packageViewModel.orderPackageResponse.observeAsState()

    // Observasi hasil order
    val response = orderResponse
    LaunchedEffect(response) {
        when (response) {
            is NetworkResponse.SUCCESS -> {
                Toast.makeText(context, "Order berhasil! ID: ${response.data.orderId}", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }
            is NetworkResponse.ERROR -> {
                Toast.makeText(context, "Gagal: ${response.message}", Toast.LENGTH_LONG).show()
            }
            is NetworkResponse.LOADING -> {
                // opsional: tampilkan loading indikator
            }
            null -> {}
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text("Minimum Portion: ${packageData.minimumPortion}", style = MaterialTheme.typography.bodyMedium)
                Text(
                    "Price per Portion: Rp${packageData.pricePerPortion}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Spacer(Modifier.height(12.dp))

        Text("Menus:", style = MaterialTheme.typography.titleMedium)

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(packageData.packageDetailDtos.size) { index ->
                val menu = packageData.packageDetailDtos[index].menu
                MenuCard(menu)
            }
        }

        Spacer(Modifier.height(78.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = if (qty == 0) "" else qty.toString(),
                onValueChange = {
                    qty = it.toIntOrNull() ?: 0
                },
                label = { Text("Qty") },
                placeholder = { Text("How many portions do you want?") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    if (qty < packageData.minimumPortion) {
                        Toast.makeText(
                            context,
                            "Minimum pemesanan adalah ${packageData.minimumPortion} porsi",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        packageViewModel.orderPackage(
                            id = packageData.packageID,
                            orderPackageRequest = OrderPackageRequest(qty)
                        )
                    }
                },
                modifier = Modifier.padding(12.dp)
            ) {
                Text("Beli Sekarang")
            }
        }
    }
}


@Composable
fun MenuCard(menu: Menu, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .width(200.dp)
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = RetrofitInstance.BASE + menu.photoUrl,
                contentDescription = menu.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(menu.name, fontWeight = FontWeight.SemiBold)
        }
    }
}
