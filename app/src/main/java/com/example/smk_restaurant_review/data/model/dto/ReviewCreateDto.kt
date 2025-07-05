package com.example.smk_restaurant_review.data.model.dto

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class ReviewCreateDto (
    val menuID: RequestBody,
    val rating: RequestBody,
    val reviewText: RequestBody,
    val photo: MultipartBody.Part? = null
)