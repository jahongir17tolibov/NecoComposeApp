package com.example.necocomposeapp.domain.networking

import android.util.Log
import com.example.necocomposeapp.domain.intent.ResponseState
import com.example.necocomposeapp.data.model.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class KtorApiService(private val httpClient: HttpClient) : ApiService {

    companion object {
        private const val BASE_URL = "https://fakestoreapi.com"
    }

    override suspend fun getStoreApi(): Flow<ResponseState<List<Product>>> = flow {
        try {
            val response = httpClient.get("$BASE_URL/products").body<String>()
            val json = Json {
                ignoreUnknownKeys = true
            }
            val product = json.decodeFromString<List<Product>>(response)
            if (product.isNotEmpty()) {
                emit(ResponseState.Success(product))
            } else {
                emit(ResponseState.Empty)
            }
        } catch (e: ResponseException) {
            emit(ResponseState.Error(e.message))
        }
    }.catch {
        emit(ResponseState.Error(it.message))
    }.flowOn(IO)

}