package com.example.smk_restaurant_review.data.remote

import com.example.smk_restaurant_review.data.model.Package
import retrofit2.Response
import retrofit2.http.GET

interface IPackage {
    @GET("packages")
    suspend fun getAllPackages() : Response<List<Package>>
}