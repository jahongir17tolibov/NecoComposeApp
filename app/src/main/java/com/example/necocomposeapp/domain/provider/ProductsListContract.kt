package com.example.necocomposeapp.domain.provider

import com.example.necocomposeapp.data.model.Product

interface ProductsListContract :
    UnidirectionalViewModel<ProductsListContract.State, ProductsListContract.Effect, ProductsListContract.Event> {

    data class State(
        val isLoading: Boolean = false,
        val products: List<Product> = listOf(),
        val error: String = ""
    )

    sealed interface Event {
        data class OnCartClick(val product: Product) : Event
        object OnGetProductsList: Event
        object IsLoading : Event
        object OnBackPressed : Event
        data class ShowToast(val message: String) : Event
    }

    sealed class Effect {
        object OnBackPressed : Effect()
        data class ShowToast(val message: String) : Effect()
    }

}