package com.example.smk_restaurant_review.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.smk_restaurant_review.data.model.OrderHistory
import com.example.smk_restaurant_review.data.model.Package
import com.example.smk_restaurant_review.data.model.dto.OrderPackageRequest
import com.example.smk_restaurant_review.data.model.dto.OrderPackageResponse
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import com.example.smk_restaurant_review.data.remote.RetrofitInstance
import com.example.smk_restaurant_review.data.repository.OrderRepository
import com.example.smk_restaurant_review.data.repository.PackageRepository
import com.example.smk_restaurant_review.ui.viewmodels.base.BaseViewModel

class PackageViewModel(private val application: Application) : BaseViewModel(application) {
    private val packageInterface = RetrofitInstance.getPackagesInterface(application.applicationContext)
    private val orderInterface = RetrofitInstance.getOrdersInterface(application.applicationContext)
    private val packageRepos = PackageRepository(packageInterface)
    private val orderRepos = OrderRepository(orderInterface)

    private var _packages = MutableLiveData<NetworkResponse<List<Package>>>()
    private var _orderPackageResult = MutableLiveData<NetworkResponse<OrderPackageResponse>>()

    val packages = _packages
    val orderPackageResponse = _orderPackageResult

    fun getAllPackages() {
        executeApiCall(_packages) {
            packageRepos.getAllPackages()
        }
    }

    fun orderPackage(id: Int, orderPackageRequest: OrderPackageRequest) {
        executeApiCall(_orderPackageResult) {
            orderRepos.orderPackage(id, orderPackageRequest)
        }

        Log.d("Hasil", "Hasil:" + (_orderPackageResult.value?.toString() ?: "null"))
    }
}