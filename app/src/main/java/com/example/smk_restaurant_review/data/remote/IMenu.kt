package com.example.smk_restaurant_review.data.remote

import com.example.smk_restaurant_review.data.model.ListMenu
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface IMenu {
    @GET("menu")
    suspend fun getAll() : Response<ListMenu>
}