package com.example.smk_restaurant_review.data.model


import com.google.gson.annotations.SerializedName

data class Package(
    @SerializedName("minimumPortion")
    val minimumPortion: Int,
    @SerializedName("packageDetailDtos")
    val packageDetailDtos: List<PackageDetailDto>,
    @SerializedName("packageID")
    val packageID: Int,
    @SerializedName("packageName")
    val packageName: String,
    @SerializedName("pricePerPortion")
    val pricePerPortion: Int
)