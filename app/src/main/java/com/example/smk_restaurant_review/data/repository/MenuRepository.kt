package com.example.smk_restaurant_review.data.repository

import com.example.smk_restaurant_review.data.remote.IMenu

class MenuRepository(private val iMenu : IMenu) {
    suspend fun getAll() = iMenu.getAll()
}