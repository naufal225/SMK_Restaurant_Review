package com.example.smk_restaurant_review.data.model


import com.google.gson.annotations.SerializedName

data class PackageDetailDto(
    @SerializedName("menu")
    val menu: Menu,
    @SerializedName("packageDetailID")
    val packageDetailID: Int,
    @SerializedName("packageID")
    val packageID: Int
)