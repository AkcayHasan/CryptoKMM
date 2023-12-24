package com.akcay.cryptokmm.network.repository

import app.cash.sqldelight.coroutines.asFlow
import com.akcay.cryptokmm.database.CryptoDatabase
import com.akcay.cryptokmm.db.mapToList
import com.akcay.cryptokmm.db.mapper.toCrypto
import com.akcay.cryptokmm.db.mapper.toCryptoEntity
import com.akcay.cryptokmm.network.entities.response.CoinInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CryptoRepositoryImpl(
    database: CryptoDatabase
) : CryptoRepository {

    private val dbQuery = database.cryptoQueries
    override fun getCoins(): Flow<List<CoinInfo>> {
        return dbQuery.getAllCoins().asFlow().mapToList().map { coins ->
            coins.map {
                it.toCrypto()
            }
        }
    }

    override suspend fun addCoin(coin: CoinInfo) {
        coin.toCryptoEntity().let {
            dbQuery.insertCoin(
                id = it.id,
                name = it.name,
                fullName = it.fullName,
                internal_ = it.internal_,
                imageUrl = it.imageUrl,
                url = it.url
            )
        }
    }

    override suspend fun deleteAllCoins() {
        dbQuery.deleteAllCoins()
    }
}