package com.example.smk_restaurant_review.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Review(
    @SerializedName("reviewID")
    val reviewID: Int,

    @SerializedName("orderID")
    val orderID: String,

    @SerializedName("menuID")
    val menuID: Int,

    @SerializedName("rating")
    val rating: Int,

    @SerializedName("reviewText")
    val reviewText: String,

    @SerializedName("photo")
    val photo: String,

    @SerializedName("createdAt")
    val createdAt: Date
)
