package com.example.smk_restaurant_review.data.remote

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "http://10.0.2.2:8000/api/"

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
}