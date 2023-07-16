package com.example.necocomposeapp.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.necocomposeapp.domain.intent.ResponseState
import com.example.necocomposeapp.domain.networking.ApiService
import com.example.necocomposeapp.domain.provider.ProductsListContract
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StoreViewModel(private val apiService: ApiService) : ViewModel(), ProductsListContract {

    private val effectChannel = Channel<ProductsListContract.Effect>(Channel.UNLIMITED)
    private val effectSharedFlow = MutableSharedFlow<ProductsListContract.Effect>()
    private val mutableState = MutableStateFlow(ProductsListContract.State())

    override val state: StateFlow<ProductsListContract.State> get() = mutableState.asStateFlow()
    override val effect: Flow<ProductsListContract.Effect> get() = effectChannel.receiveAsFlow()
    override fun event(event: ProductsListContract.Event) = when (event) {
        ProductsListContract.Event.OnGetProductsList -> getData()

        ProductsListContract.Event.IsLoading -> getData(isLoading = true)

        else -> Unit
    }

    private fun getData(isLoading: Boolean = false) {
        if (isLoading) {
            mutableState.update {
                it.copy(isLoading = true)
            }
        }

        viewModelScope.launch {
            getProductsList()
        }

    }

    private suspend fun getProductsList() = apiService.getStoreApi().onEach { result ->
        when (result) {
            is ResponseState.Success -> mutableState.update {
                it.copy(products = result.data)
            }

            is ResponseState.Loading -> mutableState.update {
                it.copy(isLoading = true)
            }

            is ResponseState.Error -> mutableState.update {
                showToast(result.message ?: "An unexpected error occurred")
                it.copy(error = result.message ?: "An unexpected error occurred")
            }

            else -> Unit
        }
    }.launchIn(viewModelScope)

    private fun onBackPressed() = viewModelScope.launch {
        effectChannel.trySend(ProductsListContract.Effect.OnBackPressed)
    }

    private fun showToast(message: String) = viewModelScope.launch {
        effectChannel.trySend(ProductsListContract.Effect.ShowToast(message = message))
    }


//    old version for getData and fetch in viewModel using MVVM
    /** private val _products = MutableStateFlow<ResponseState<List<Product>>>(ResponseState.Empty)
    val products: StateFlow<ResponseState<List<Product>>> get() = _products.asStateFlow()

    private fun loadProducts() = viewModelScope.launch {
    apiService.getStoreApi()
    .onStart {
    _products.value = ResponseState.Loading
    }.collect { data ->
    _products.value = data
    Log.d("jt1771tj", "loadProducts: $data")
    }
    } **/
}