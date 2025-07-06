package com.example.smk_restaurant_review.data.model


import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("menuID")
    val menuID: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("photoUrl")
    val photoUrl: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("isReviewed")
    val isReviewed: Boolean,
    @SerializedName("isOrdered")
    val isOrdered: Boolean,
)