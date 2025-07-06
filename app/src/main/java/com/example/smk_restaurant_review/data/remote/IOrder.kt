package com.example.smk_restaurant_review.data.remote

import com.example.smk_restaurant_review.data.model.OrderHistory
import retrofit2.Response
import retrofit2.http.GET

interface IOrder {
    @GET("orders")
    suspend fun getOrdersHistory() : Response<OrderHistory>
}