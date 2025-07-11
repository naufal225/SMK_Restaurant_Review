package com.example.smk_restaurant_review.data.remote

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "http://10.0.2.2:7045/api/"
    const val BASE = "http://10.0.2.2:7045/"

    fun getRetrofit(context: Context) : Retrofit {
        val sharedPrefsManager = SharedPrefsManager(context)
        val authInterceptor = AuthInterceptor(sharedPrefsManager)

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getAuthInterface(context: Context) : IAuth {
        return getRetrofit(context).create(IAuth::class.java)
    }

    fun getMenuInterface(context: Context) : IMenu {
        return getRetrofit(context).create(IMenu::class.java)
    }

    fun getReviewInterface(context: Context) : IReview {
        return getRetrofit(context).create(IReview::class.java)
    }

    fun getOrdersInterface(context: Context) : IOrder {
        return getRetrofit(context).create(IOrder::class.java)
    }

    fun getPackagesInterface(context: Context) : IPackage {
        return getRetrofit(context).create(IPackage::class.java)
    }
}