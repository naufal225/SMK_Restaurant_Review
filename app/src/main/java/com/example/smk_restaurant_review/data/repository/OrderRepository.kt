package com.example.smk_restaurant_review.data.repository

import com.example.smk_restaurant_review.data.model.dto.OrderPackageRequest
import com.example.smk_restaurant_review.data.remote.IOrder

class OrderRepository(private val iOrder: IOrder) {
    suspend fun getOrdersHistory() = iOrder.getOrdersHistory()

    suspend fun orderPackage(id: Int, orderPackageRequest: OrderPackageRequest) = iOrder.orderPackage(id, orderPackageRequest)
}