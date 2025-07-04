package com.example.smk_restaurant_review.data.remote

import android.content.Context

class SharedPrefsManager(context: Context) {
    val sharedPreferences = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("TOKEN", token).apply()
    }

    fun getToken() : String? {
        return sharedPreferences.getString("TOKEN", null)
    }
}