package com.akcay.cryptokmm.network.repository

import com.akcay.cryptokmm.network.entities.response.CoinInfo
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    fun getCoins(): Flow<List<CoinInfo>>

    suspend fun addCoin(coin: CoinInfo)

    suspend fun deleteAllCoins()
}