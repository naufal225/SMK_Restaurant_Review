package com.example.smk_restaurant_review.ui.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.smk_restaurant_review.data.model.ListMenu
import com.example.smk_restaurant_review.data.model.Menu
import com.example.smk_restaurant_review.data.model.OrderHistory
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.data.remote.RetrofitInstance
import com.example.smk_restaurant_review.data.repository.MenuRepository
import com.example.smk_restaurant_review.data.repository.OrderRepository
import com.example.smk_restaurant_review.ui.viewmodels.base.BaseViewModel

class OrderViewModel(private val application: Application) : BaseViewModel(application) {
    private val orderInterface = RetrofitInstance.getOrdersInterface(application.applicationContext)
    private val repos = OrderRepository(orderInterface)

    private var _orders = MutableLiveData<NetworkResponse<OrderHistory>>()

    val menus = _orders

    fun getAllOrdersHistory() {
        executeApiCall(_orders) {
            repos.getOrdersHistory()
        }
    }
}