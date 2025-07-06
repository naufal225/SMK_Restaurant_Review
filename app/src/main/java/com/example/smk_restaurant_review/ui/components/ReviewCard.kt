package com.example.smk_restaurant_review.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smk_restaurant_review.data.model.Review
import java.text.SimpleDateFormat
import java.util.*

fun formatTanggalLegacy(isoDate: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMM yyyy HH:mm", Locale("id", "ID"))
    val date = parser.parse(isoDate)
    return formatter.format(date)
}

@Composable
fun ReviewCard(
    review: Review,
    onDeleteClick: (() -> Unit)? = null, // optional delete callback
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            // Nama & menu titik tiga
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = review.reviewerName ?: "Anonim",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        text = formatTanggalLegacy(review.createdAt),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    StarRatingBarNoChange(rating = review.rating)
                }

                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu"
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Hapus review") },
                            onClick = {
                                expanded = false
                                onDeleteClick?.invoke()
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = review.reviewText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Composable
fun StarRatingBarNoChange(
    rating: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.StarBorder,
                contentDescription = "Star $i",
                tint = Color(0xFFFFD700), // Gold
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp)
            )
        }
    }
}
