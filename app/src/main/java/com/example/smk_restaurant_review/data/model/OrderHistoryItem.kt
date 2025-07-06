package com.example.smk_restaurant_review.data.model


import com.google.gson.annotations.SerializedName

data class OrderHistoryItem(
    @SerializedName("bank")
    val bank: String,
    @SerializedName("cardNumber")
    val cardNumber: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("detailOrders")
    val detailOrders: List<DetailOrder>,
    @SerializedName("orderID")
    val orderID: String,
    @SerializedName("payment")
    val payment: String,
    @SerializedName("paymentStatus")
    val paymentStatus: String
)