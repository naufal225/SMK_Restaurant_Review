package com.example.smk_restaurant_review.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.smk_restaurant_review.data.model.Menu

//
//@Composable
//fun ProductCard(
//    barang : Barang,
//    selectedItems : SnapshotStateMap<Int, Int>,
//    onSelectedChange : (Int, Int) -> Unit
//) {
//    val quanitity = selectedItems[barang.id] ?: 0
//
//    Card (
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(2.dp),
//        modifier = Modifier.fillMaxWidth().padding(6.dp),
//        colors = CardDefaults.cardColors(Color.White)
//    ) {
//        Column(
//            modifier = Modifier.padding(8.dp)
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                AsyncImage(
//                    model = barang.urlGambar,
//                    modifier = Modifier.size(90.dp).clip(RoundedCornerShape(12.dp)),
//                    contentDescription = "${barang.nama}",
//                    contentScale = ContentScale.Crop
//                )
//
//                Spacer(Modifier.width(8.dp))
//
//                Column(
//                    Modifier.weight(1f)
//                ) {
//                    Text(
//                        text = barang.nama,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        maxLines = 2,
//                        overflow = TextOverflow.Ellipsis
//                    )
//
//                    Spacer(Modifier.height(8.dp))
//
//                    Text(
//                        text = "Rp." + barang.harga.toString(),
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Medium,
//                        maxLines = 2,
//                        overflow = TextOverflow.Ellipsis
//                    )
//
//                    Spacer(Modifier.height(4.dp))
//
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Star,
//                            contentDescription = "Rating",
//                            tint = Color.Yellow
//                        )
//
//                        Spacer(Modifier.width(2.dp))
//
//                        Text(
//                            text = barang.rating,
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 14.sp
//                        )
//
//                        Spacer(Modifier.width(8.dp))
//
//                        Text(
//                            text = "Stok: ${barang.stok}",
//                            fontWeight = FontWeight.SemiBold,
//                            fontSize = 14.sp
//                        )
//                    }
//                }
//
//
//            }
//            Spacer(Modifier.height(12.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//                if (quanitity > 0) {
//                    IconButton(
//                        onClick = {
//                            onSelectedChange(barang.id, quanitity - 1)
//                        },
//                        modifier = Modifier.background(Color.Blue).size(36.dp),
//
//                        ) {
//                        Icon(
//                            imageVector = Icons.Default.Remove,
//                            tint = Color.White,
//                            contentDescription = "delete"
//                        )
//                    }
//
//                    Spacer(Modifier.width(12.dp))
//
//                    Text(
//                        text = quanitity.toString(),
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        modifier = Modifier.padding(horizontal = 4.dp)
//                    )
//
//                    Spacer(Modifier.width(12.dp))
//                }
//
//                IconButton(
//                    onClick = {
//                        onSelectedChange(barang.id, quanitity + 1)
//                    },
//                    modifier = Modifier.background(Color.Blue).size(36.dp),
//                    enabled = quanitity < barang.stok
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Add,
//                        contentDescription = "add",
//                        tint = Color.White
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun ProductCard(
    menu: Menu,
    onSelectedChange: (Int, Int) -> Unit,
    selectedItems: SnapshotStateMap<Int, Int>
) {
    val quantity = selectedItems[menu.menuID] ?: 0

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier.fillMaxWidth().padding(6.dp),
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
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = menu.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "Rp. ${menu.price}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(Modifier.height(4.dp))



                        Spacer(Modifier.width(8.dp))

                    }

                }


            }

        }
    }

//
//@Preview(showBackground = true)
//@Composable
//fun PreviewProductCard() {
//    // Dummy data
//    val barang = Barang(
//        id = 1,
//        nama = "Kopi Susu Gula Aren",
//        harga = 18000,
//        rating = "4.8",
//        stok = 15,
//        urlGambar = "https://via.placeholder.com/150",
//        createdAt = "",
//        updatedAt = "" // Gambar dummy
//    )
//
//    // State dummy pakai remember
//    val selectedItems = remember {
//        mutableStateMapOf<Int, Int>().apply {
//            put(barang.id, 2) // Simulasi beli 2
//        }
//    }
//
//    // Fungsi kosong untuk preview
//    val onSelectedChange: (Int, Int) -> Unit = { _, _ -> }
//
//    ProductCard(
//        barang = barang,
//        selectedItems = selectedItems,
//        onSelectedChange = onSelectedChange
//    )
//}