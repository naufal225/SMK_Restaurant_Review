package com.example.smk_restaurant_review.ui.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.smk_restaurant_review.data.model.ListMenu
import com.example.smk_restaurant_review.data.model.LoginResponse
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.data.remote.RetrofitInstance
import com.example.smk_restaurant_review.data.repository.AuthRepository
import com.example.smk_restaurant_review.data.repository.MenuRepository
import com.example.smk_restaurant_review.ui.viewmodels.base.BaseViewModel

class MenuViewModel(private val application: Application) : BaseViewModel(application) {
    private val menuInterface = RetrofitInstance.getMenuInterface(application.applicationContext)
    private val repos = MenuRepository(menuInterface)

    private var _menus = MutableLiveData<NetworkResponse<ListMenu>>()

    val menus = _menus

    fun GetAll() {
        executeApiCall(_menus) {
            repos.getAll()
        }
    }

}