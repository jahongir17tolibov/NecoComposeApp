package com.example.necocomposeapp.domain.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.necocomposeapp.domain.intent.ResponseState
import com.example.necocomposeapp.domain.networking.ApiService
import com.example.necocomposeapp.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class StoreViewModel(private val apiService: ApiService) : ViewModel() {

    private val _products = MutableStateFlow<ResponseState<List<Product>>>(ResponseState.Empty)
    val products: StateFlow<ResponseState<List<Product>>> get() = _products.asStateFlow()

    fun loadProducts() = viewModelScope.launch {
        apiService.getStoreApi()
            .onStart {
                _products.value = ResponseState.Loading
            }.collect { data ->
                _products.value = data
                Log.d("jt1771tj", "loadProducts: $data")
            }
    }

}