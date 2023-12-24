package com.akcay.cryptokmm.ui.screens.detailscreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.akcay.cryptokmm.network.api.CoinApi
import com.akcay.cryptokmm.network.entities.response.CoinNewsModelItem
import com.akcay.cryptokmm.network.entities.response.Data
import com.akcay.cryptokmm.network.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    private val coinApi: CoinApi,
    private val cryptoRepository: CryptoRepository
): ScreenModel {
    private val _coinNews = MutableStateFlow(ArrayList<CoinNewsModelItem>())

    val coinNews = _coinNews.stateIn(screenModelScope, SharingStarted.Lazily, emptyList())

    fun addCoinToDatabase(coin: Data) {
        screenModelScope.launch {
            cryptoRepository.addCoin(coin)
        }
    }

    fun getCoinNews() {
        screenModelScope.launch {
            coinApi.getCoinNews().collectLatest {
                _coinNews.value = it
            }
        }
    }
}