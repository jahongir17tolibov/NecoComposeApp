package com.example.necocomposeapp.domain.networking

import com.example.necocomposeapp.domain.intent.ResponseState
import com.example.necocomposeapp.model.Product
import kotlinx.coroutines.flow.Flow

interface ApiService {
    suspend fun getStoreApi(): Flow<ResponseState<List<Product>>>
}