package com.example.smk_restaurant_review.data.model.dto

import com.google.gson.annotations.SerializedName

data class UserDto (
    @SerializedName("name")
    val name : String,
    @SerializedName("email")
    val email : String,
)