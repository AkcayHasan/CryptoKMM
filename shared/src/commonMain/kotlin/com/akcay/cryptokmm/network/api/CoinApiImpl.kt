package com.akcay.cryptokmm.network.api

import com.akcay.cryptokmm.network.entities.response.CoinListModel
import com.akcay.cryptokmm.network.entities.response.CoinNewsModelItem
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
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class CoinApiImpl : CoinApi {
    override suspend fun getAllCoinList(): Flow<CoinListModel> = flow {
        emit(
            client.get {
                headers {
                    append(
                        "authorization",
                        "a17642370a98b643021e72241aa4de538957c0b08a0c461b7a034d591a3aa7a7"
                    )
                    coinEndPoints("data/top/totalvolfull?limit=20&tsym=USD")
                }
            }.body()


        )
    }

    override suspend fun getCoinNews(): Flow<ArrayList<CoinNewsModelItem>> = flow {
        emit(
            client.get {
                headers {
                    append(
                        "authorization",
                        "a17642370a98b643021e72241aa4de538957c0b08a0c461b7a034d591a3aa7a7"
                    )
                    coinEndPoints("data/news/feeds")
                }
            }.body()
        )
    }

    /*
    return client.get {
            headers {
                append("authorization: Apikey", "a17642370a98b643021e72241aa4de538957c0b08a0c461b7a034d591a3aa7a7")
                coinEndPoints("data/top/totalvolfull?limit=20&tsym=USD")
            }
        }.body()
     */

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
            takeFrom("https://min-api.cryptocompare.com/")
            encodedPath = path
        }

    }
}