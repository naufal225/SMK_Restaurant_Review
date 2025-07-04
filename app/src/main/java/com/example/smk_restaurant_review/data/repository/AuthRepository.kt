package com.example.smk_restaurant_review.data.repository

import com.example.smk_restaurant_review.data.model.LoginRequest
import com.example.smk_restaurant_review.data.remote.IAuth

class AuthRepository(private val iAuth : IAuth) {
    suspend fun login(loginRequest: LoginRequest) = iAuth.login(loginRequest.email,loginRequest.password)
}