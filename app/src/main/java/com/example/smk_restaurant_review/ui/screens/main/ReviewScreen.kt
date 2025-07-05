package com.example.smk_restaurant_review.ui.screens.main

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.smk_restaurant_review.data.model.LoginResponse
import com.example.smk_restaurant_review.data.model.Review
import com.example.smk_restaurant_review.data.model.dto.ReviewCreateDto
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.data.remote.SharedPrefsManager
import com.example.smk_restaurant_review.ui.navigation.Screen
import com.example.smk_restaurant_review.ui.viewmodels.ReviewViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import kotlin.io.path.Path
import kotlin.math.roundToInt

@Composable
fun ReviewScreen(
    id: Int,
    navController: NavController,
    reviewViewModel: ReviewViewModel, // tidak dipakai dulu
    modifier: Modifier = Modifier
) {
    var rating by remember { mutableIntStateOf(0) }
    var reviewText by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    val reviewPostResponse by reviewViewModel.reviewPostResponse.observeAsState()

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    LaunchedEffect(reviewPostResponse) {
        when (reviewPostResponse) {
            is NetworkResponse.ERROR -> {
                reviewViewModel.reviewPostResponse.postValue(null)

            }

            NetworkResponse.LOADING -> {}
            is NetworkResponse.SUCCESS -> {
                reviewViewModel.reviewPostResponse.postValue(null)
                navController.popBackStack()
            }

            null -> {}
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Berikan Review",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(text = "Rating: $rating ⭐")

        StarRatingBar(
            rating = rating,
            onRatingChanged = { rating = it },
            modifier = Modifier.padding(vertical = 8.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = reviewText,
            onValueChange = { reviewText = it },
            label = { Text("Tulis ulasanmu di sini") },
            modifier = Modifier.fillMaxWidth(),
            enabled = (rating > 0)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                imagePicker.launch("image/*")
            },
            enabled = (rating > 0)
        ) {
            Text("Pilih Gambar")
        }

        Spacer(modifier = Modifier.height(12.dp))

        selectedImageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Gambar Review",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val photoPart = selectedImageUri?.let {
                    val file = uriToFile(selectedImageUri!!, context)
                    val body = file.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("photo", file.name, body)
                }
                reviewViewModel.PostReview(
                    ReviewCreateDto(
                        menuID = id.toString().toRequestBody("text/plain".toMediaType()),
                        rating = rating.toString().toRequestBody("text/plain".toMediaType()),
                        reviewText = reviewText.toRequestBody("text/plain".toMediaType()),
                        photo = photoPart
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = (rating > 0)
        ) {
            Text("Kirim Review")
        }
    }
}

@Composable
fun StarRatingBar(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.StarBorder,
                contentDescription = "Star $i",
                tint = Color(0xFFFFD700), // Gold
                modifier = Modifier
                    .size(66.dp)
                    .padding(4.dp)
                    .clickable { onRatingChanged(i) }
            )
        }
    }
}

fun uriToFile(uri: Uri, context: Context) : File {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.cacheDir, "File_upload_${System.currentTimeMillis()}.jpg")
    inputStream?.use { input ->
        FileOutputStream(file).use { output -> input.copyTo(output) }
    }
    return file
}