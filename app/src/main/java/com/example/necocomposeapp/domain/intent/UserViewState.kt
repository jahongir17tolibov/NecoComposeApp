package com.example.necocomposeapp.domain.intent

import com.example.necocomposeapp.model.Product

sealed interface UserViewState {
    object Loading : UserViewState
    data class Success(val data: Product) : UserViewState
    data class Error(val message: String? = null)
}