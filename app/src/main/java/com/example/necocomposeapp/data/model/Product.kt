package com.example.necocomposeapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int? = null,
    val title: String? = null,
    val price: Double? = null,
    val description: String? = null,
    val category: String? = null,
    val image: String? = null,
    val rating: RatingModel? = null
) {

    @Serializable
    data class RatingModel(val rate: Double? = null, val count: Int? = null)

}