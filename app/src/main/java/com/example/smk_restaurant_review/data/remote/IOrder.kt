package com.example.smk_restaurant_review.data.remote

import com.example.smk_restaurant_review.data.model.OrderHistory
import com.example.smk_restaurant_review.data.model.dto.OrderPackageRequest
import com.example.smk_restaurant_review.data.model.dto.OrderPackageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IOrder {
    @GET("orders")
    suspend fun getOrdersHistory() : Response<OrderHistory>

    @POST("orders/package/{id}")
    suspend fun orderPackage(
        @Path("id") id: Int,
        @Body request: OrderPackageRequest
    ): Response<OrderPackageResponse>
}