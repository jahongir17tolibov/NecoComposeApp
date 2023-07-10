package com.example.necocomposeapp.domain.intent


sealed class ResponseState<out R> {
    object Loading : ResponseState<Nothing>()
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val message: String? = null) : ResponseState<Nothing>()
    object Empty : ResponseState<Nothing>()
}
