package com.example.smk_restaurant_review.data.model


import com.google.gson.annotations.SerializedName

data class DetailOrder(
    @SerializedName("menuID")
    val menuID: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("qty")
    val qty: Int,
    @SerializedName("status")
    val status: String
)