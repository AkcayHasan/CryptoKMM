package com.akcay.cryptokmm.ui.screens.favouritescreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.akcay.cryptokmm.network.entities.response.CoinInfo
import com.akcay.cryptokmm.network.entities.response.CoinListModel
import com.akcay.cryptokmm.network.repository.CryptoRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavouriteScreenViewModel(
    private val cryptoRepository: CryptoRepository
) : ScreenModel {


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private var _coinList = MutableStateFlow(emptyList<CoinInfo>()).asStateFlow()

    @OptIn(FlowPreview::class)
    val coinList = _coinList.combine(_searchText.debounce(500L)) { coinList, searchText ->
        coinList.filter {
            it.fullName?.lowercase()?.contains(searchText.lowercase()) ?: false
        }
    }.stateIn(screenModelScope, SharingStarted.Lazily, emptyList())

    fun onSearchQueryChange(text: String) {
        _searchText.value = text
    }

    val coinTempList = cryptoRepository.getCoins().map {
        it
    }.stateIn(
        scope = screenModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    private fun getAllFavouriteCoins() {
        _coinList = cryptoRepository.getCoins().map {
            it
        }.stateIn(
            scope = screenModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
    }

}