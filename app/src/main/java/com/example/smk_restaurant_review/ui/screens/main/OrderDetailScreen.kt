package com.example.smk_restaurant_review.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smk_restaurant_review.data.model.OrderHistoryItem
import java.text.NumberFormat
import java.util.Locale


@Composable
fun OrderDetailScreen(
    order: OrderHistoryItem,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Detail Pesanan",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth(),

        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoRow("Order ID", order.orderID)
                InfoRow("Tanggal", order.date)
                InfoRow("Bank", order.bank)
                InfoRow("Metode Pembayaran", order.payment)
                InfoRow("Status Pembayaran", order.paymentStatus)
                InfoRow("Nomor Kartu", order.cardNumber)
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Item Dipesan", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        order.detailOrders.forEach { item ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(Color.White)

            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Menu ID: ${item.menuID}", fontWeight = FontWeight.SemiBold)
                        Text("Qty: ${item.qty}", style = MaterialTheme.typography.bodySmall)
                        Text("Status: ${item.status}", style = MaterialTheme.typography.bodySmall)
                    }

                    Text(
                        formatRupiah(item.qty * item.price),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.Medium)
        Text(value, fontWeight = FontWeight.Normal)
    }
}

fun formatRupiah(amount: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    return format.format(amount).replace("Rp", "Rp ")
}