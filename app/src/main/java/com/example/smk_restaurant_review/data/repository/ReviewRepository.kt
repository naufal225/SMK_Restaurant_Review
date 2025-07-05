package com.example.smk_restaurant_review.data.repository

import com.example.smk_restaurant_review.data.model.dto.ReviewCreateDto
import com.example.smk_restaurant_review.data.remote.IMenu
import com.example.smk_restaurant_review.data.remote.IReview

class ReviewRepository(private val iReview: IReview) {
    suspend fun getReviewById(id : Int) = iReview.getReviewById(id)

    suspend fun postReview(reviewCreateDto: ReviewCreateDto) = iReview.postReview(
        menuId = reviewCreateDto.menuID,
        rating = reviewCreateDto.rating,
        reviewText = reviewCreateDto.reviewText,
        photo = reviewCreateDto.photo
    )
}