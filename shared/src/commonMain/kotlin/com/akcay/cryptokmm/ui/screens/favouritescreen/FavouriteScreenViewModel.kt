package com.akcay.cryptokmm.ui.screens.favouritescreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.akcay.cryptokmm.network.entities.response.CoinListModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn

class FavouriteScreenViewModel(

): ScreenModel {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _coinList = MutableStateFlow(CoinListModel())

    @OptIn(FlowPreview::class)
    val coinList = _coinList.combine(_searchText.debounce(500L)) { coinList, searchText ->
        coinList.data.filter {
            it.coinInfo?.fullName?.lowercase()?.contains(searchText.lowercase()) ?: false
        }
    }.stateIn(screenModelScope, SharingStarted.Lazily, emptyList())

    fun onSearchQueryChange(text: String) {
        _searchText.value = text
    }

}