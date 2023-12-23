package com.akcay.cryptokmm.network.api

import com.akcay.cryptokmm.network.entities.response.CoinListModel
import com.akcay.cryptokmm.network.entities.response.CoinNewsModelItem
import kotlinx.coroutines.flow.Flow

interface CoinApi {
    suspend fun getAllCoinList(): Flow<CoinListModel>
    suspend fun getCoinNews(): Flow<ArrayList<CoinNewsModelItem>>
}