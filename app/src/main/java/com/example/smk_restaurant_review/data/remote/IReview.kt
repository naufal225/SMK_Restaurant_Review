package com.example.smk_restaurant_review.data.remote

import com.example.smk_restaurant_review.data.model.Review
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IReview {
    @GET("review/menu/{id}")
    suspend fun getReviewById(@Path("id") id : Int) : Response<List<Review>>
}