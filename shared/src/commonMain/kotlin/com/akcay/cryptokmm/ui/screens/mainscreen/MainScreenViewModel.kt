package com.akcay.cryptokmm.ui.screens.mainscreen

import com.akcay.cryptokmm.network.api.CoinApiImpl
import com.akcay.cryptokmm.network.entities.response.CoinListModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(private val coinApiImpl: CoinApiImpl): ViewModel() {

    private var _coinList = MutableStateFlow(CoinListModel())
    var coinList = _coinList.asStateFlow()

    fun getAllCoinList() {
        viewModelScope.launch {
            coinApiImpl.getAllCoinList().collectLatest {
                _coinList.value = it
            }
        }
    }
}