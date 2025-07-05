package com.example.smk_restaurant_review.ui.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.smk_restaurant_review.data.model.LoginRequest
import com.example.smk_restaurant_review.data.model.LoginResponse
import com.example.smk_restaurant_review.data.model.Review
import com.example.smk_restaurant_review.data.model.dto.ReviewCreateDto
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.data.remote.RetrofitInstance
import com.example.smk_restaurant_review.data.remote.SharedPrefsManager
import com.example.smk_restaurant_review.data.repository.AuthRepository
import com.example.smk_restaurant_review.data.repository.ReviewRepository
import com.example.smk_restaurant_review.ui.viewmodels.base.BaseViewModel

class ReviewViewModel(private val application: Application) : BaseViewModel(application) {
    private val reviewInterface = RetrofitInstance.getReviewInterface(application.applicationContext)
    private val repos = ReviewRepository(reviewInterface)

    private var _reviewsByMenu = MutableLiveData<NetworkResponse<List<Review>>>()
    private var _reviewPostResponse = MutableLiveData<NetworkResponse<Review>>()
    private var _reviewDeleteResponse = MutableLiveData<NetworkResponse<Unit>>()

    val reviewsByMenu = _reviewsByMenu
    val reviewPostResponse = _reviewPostResponse
    val reviewDeleteResponse = _reviewDeleteResponse

    fun GetReviews(id: Int) {
        executeApiCall(_reviewsByMenu) {
            repos.getReviewById(id)
        }
    }

    fun PostReview(reviewCreateDto: ReviewCreateDto) {
        executeApiCall(_reviewPostResponse) {
            repos.postReview(reviewCreateDto)
        }
    }

    fun DeleteReview(id : Int) {
        executeApiCall(_reviewDeleteResponse) {
            repos.deleteReview(id)
        }
    }


}