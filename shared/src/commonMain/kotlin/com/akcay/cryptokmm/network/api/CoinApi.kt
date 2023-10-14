package com.akcay.cryptokmm.network.api

import com.akcay.cryptokmm.network.entities.response.CoinListModel
import kotlinx.coroutines.flow.Flow

interface CoinApi {
    suspend fun getAllCoinList(): Flow<List<CoinListModel>>
}