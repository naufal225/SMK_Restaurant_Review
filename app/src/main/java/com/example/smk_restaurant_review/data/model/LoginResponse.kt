package com.example.smk_restaurant_review.data.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String
)