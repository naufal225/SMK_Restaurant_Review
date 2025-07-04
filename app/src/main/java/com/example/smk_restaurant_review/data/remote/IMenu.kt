package com.example.smk_restaurant_review.data.remote

import com.example.smk_restaurant_review.data.model.ListMenu
import com.example.smk_restaurant_review.data.model.Menu
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface IMenu {
    @GET("menu")
    suspend fun getAll() : Response<ListMenu>

    @GET("menu/{id}")
    suspend fun getById(@Path("id") id: Int) : Response<Menu>
}