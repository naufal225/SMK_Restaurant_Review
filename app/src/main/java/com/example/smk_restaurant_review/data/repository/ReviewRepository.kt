package com.example.smk_restaurant_review.data.repository

import com.example.smk_restaurant_review.data.remote.IMenu
import com.example.smk_restaurant_review.data.remote.IReview

class ReviewRepository(private val iReview: IReview) {
    suspend fun getReviewById(id : Int) = iReview.getReviewById(id)

}