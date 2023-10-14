package com.akcay.cryptokmm.network.api

import com.akcay.cryptokmm.network.entities.response.CoinListModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

class CoinApiImpl: CoinApi {
    override suspend fun getAllCoinList(): Flow<List<CoinListModel>> {
        return client.get {
            headers {
                coinEndPoints("asdasd")
            }
        }.body()
    }

    private val client = HttpClient {
        expectSuccess = true
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true })
        }
    }

    private fun HttpRequestBuilder.coinEndPoints(path: String) {
        url {
            takeFrom("https://api.spotify.com/v1/")
            encodedPath = path
        }

    }
}