package com.example.smk_restaurant_review.data.remote

import com.example.smk_restaurant_review.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface IAuth {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Response<LoginResponse>
}