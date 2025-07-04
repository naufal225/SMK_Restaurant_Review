package com.example.smk_restaurant_review.ui.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.smk_restaurant_review.data.model.LoginRequest
import com.example.smk_restaurant_review.data.model.LoginResponse
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.data.remote.RetrofitInstance
import com.example.smk_restaurant_review.data.remote.SharedPrefsManager
import com.example.smk_restaurant_review.data.repository.AuthRepository
import com.example.smk_restaurant_review.ui.viewmodels.base.BaseViewModel

class AuthViewModel(private val application: Application) : BaseViewModel(application) {
    private val sharedPrefsManager = SharedPrefsManager(application.applicationContext)
    private val authInterface = RetrofitInstance.getAuthInterface(application.applicationContext)
    private val repos = AuthRepository(authInterface)

    private var _login_result = MutableLiveData<NetworkResponse<LoginResponse>>()

    val login_result = _login_result

    fun Login(loginRequest: LoginRequest) {
        executeApiCall(_login_result) {
            repos.login(loginRequest)
        }
    }

}