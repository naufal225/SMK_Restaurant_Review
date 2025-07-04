package com.example.smk_restaurant_review.ui.viewmodels.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.smk_restaurant_review.data.remote.NetworkResponse
import kotlinx.coroutines.launch

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    fun <T> executeApiCall(
        liveData: MutableLiveData<NetworkResponse<T>>,
        apiCall: suspend () -> retrofit2.Response<T>
    ) {
        liveData.postValue(NetworkResponse.LOADING)
        viewModelScope.launch {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveData.postValue(NetworkResponse.SUCCESS(it))
                    } ?: liveData.postValue(NetworkResponse.ERROR("Data kosong"))
                } else {
                    liveData.postValue(NetworkResponse.ERROR(response.message()))
                }
            } catch (e: Exception) {
                Log.e("ApiCallError", e.message ?: "Unknown error")
                liveData.postValue(NetworkResponse.ERROR("Kesalahan teknis"))
            }
        }
    }
}
