package com.akcay.cryptokmm.network.repository

import com.akcay.cryptokmm.network.entities.response.CoinInfo
import com.akcay.cryptokmm.network.entities.response.Data
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    fun getCoins(): Flow<List<Data>>

    suspend fun addCoin(coin: Data)

    suspend fun deleteAllCoins()
}