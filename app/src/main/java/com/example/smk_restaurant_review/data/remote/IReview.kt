package com.example.smk_restaurant_review.data.remote

import com.example.smk_restaurant_review.data.model.Review
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface IReview {
    @GET("review/menu/{id}")
    suspend fun getReviewById(@Path("id") id : Int) : Response<List<Review>>

    @Multipart
    @POST("review")
    suspend fun postReview(
        @Part("menuID") menuId: RequestBody,
        @Part("rating") rating: RequestBody,
        @Part("reviewText") reviewText: RequestBody,
        @Part photo: MultipartBody.Part? = null
    ): Response<Review>

    @DELETE("review/{id}")
    suspend fun deleteReview(@Path("id") id : Int) : Response<Unit>

}