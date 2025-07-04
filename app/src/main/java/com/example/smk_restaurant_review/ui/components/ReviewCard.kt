package com.example.smk_restaurant_review.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smk_restaurant_review.data.model.Menu
import com.example.smk_restaurant_review.data.model.Review
import com.example.smk_restaurant_review.ui.navigation.Screen


@Composable
fun ReviewCard(
    review: Review,
) {

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = review.reviewText,
                            maxLines = 2,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = review.createdAt.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

            }


        }

    }
}
