package com.akcay.cryptokmm.ui.screens.mainscreen

import com.akcay.cryptokmm.network.api.CoinApiImpl
import com.akcay.cryptokmm.network.entities.response.CoinListModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainScreenViewModel(private val coinApiImpl: CoinApiImpl) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _coinList = MutableStateFlow(CoinListModel())
    @OptIn(FlowPreview::class)
    val coinList = _coinList.combine(_searchText.debounce(500L)) { coinList, searchText ->
        coinList.data.filter {
            it.coinInfo?.fullName?.lowercase()?.contains(searchText.lowercase()) ?: false
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getAllCoinList() {
        viewModelScope.launch {
            coinApiImpl.getAllCoinList().collectLatest {
                _coinList.value = it
            }
        }
    }

    fun onSearchQueryChange(text: String) {
        _searchText.value = text
    }
}