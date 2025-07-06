package com.example.smk_restaurant_review.data.model


import com.example.smk_restaurant_review.data.model.dto.UserDto
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("userDto")
    val user: UserDto
)