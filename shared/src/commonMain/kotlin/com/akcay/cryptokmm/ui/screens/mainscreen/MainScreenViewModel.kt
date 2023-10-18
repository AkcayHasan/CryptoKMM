package com.akcay.cryptokmm.ui.screens.mainscreen

import com.akcay.cryptokmm.network.api.CoinApiImpl
import com.akcay.cryptokmm.network.entities.response.CoinListModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel(private val coinApiImpl: CoinApiImpl) {

    private var _coinList = MutableStateFlow(CoinListModel())
    var coinList = _coinList.asStateFlow()

    suspend fun getAllCoinList() {
        coinApiImpl.getAllCoinList().collect {
            _coinList.value = it
        }
    }
}