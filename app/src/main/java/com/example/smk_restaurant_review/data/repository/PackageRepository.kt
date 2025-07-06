package com.example.smk_restaurant_review.data.repository

import com.example.smk_restaurant_review.data.remote.IPackage

class PackageRepository(private val iPackage: IPackage) {
    suspend fun getAllPackages() = iPackage.getAllPackages()
}