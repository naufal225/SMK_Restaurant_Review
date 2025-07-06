package com.example.smk_restaurant_review.data.model.dto


import com.google.gson.annotations.SerializedName

data class OrderPackageResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("orderId")
    val orderId: String,
    @SerializedName("packageName")
    val packageName: String,
    @SerializedName("portionQty")
    val portionQty: Int,
    @SerializedName("totalPrice")
    val totalPrice: Int
)