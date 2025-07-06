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

    fun saveName(name: String) {
        sharedPreferences.edit().putString("NAME", name).apply()
    }

    fun getName() : String? {
        return sharedPreferences.getString("NAME", null)
    }

    fun saveEmail(email: String) {
        sharedPreferences.edit().putString("EMAIL", email).apply()
    }

    fun getEmail() : String? {
        return sharedPreferences.getString("EMAIL", null)
    }

    fun clear()  {
        return sharedPreferences.edit().clear().apply()
    }
}