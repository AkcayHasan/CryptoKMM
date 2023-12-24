package com.akcay.cryptokmm.ui.screens.homescreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.akcay.cryptokmm.network.api.CoinApi
import com.akcay.cryptokmm.network.entities.response.CoinInfo
import com.akcay.cryptokmm.network.entities.response.CoinListModel
import com.akcay.cryptokmm.network.entities.response.Data
import com.akcay.cryptokmm.network.repository.CryptoRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val coinApi: CoinApi,

) : ScreenModel {
    private val _searchText = MutableStateFlow("")
    private val _sortingOption = MutableStateFlow(SortOptions.DEFAULT)
    val searchText = _searchText.asStateFlow()
    private val sortingOption = _sortingOption.asStateFlow()

    private val _coinList = MutableStateFlow(CoinListModel())

    @OptIn(FlowPreview::class)
    val coinList = _coinList.combine(_searchText.debounce(500L)) { coinList, searchText ->
        coinList.data.filter {
            it.coinInfo?.fullName?.lowercase()?.contains(searchText.lowercase()) ?: false
        }
            when(sortingOption.value){
                SortOptions.DEFAULT -> coinList.data
                SortOptions.PRICE -> coinList.data.sortedBy { it.display?.usd?.price }
                SortOptions.PRICE_REVERSED -> coinList.data.sortedBy { it.display?.usd?.price }.reversed()
                SortOptions.NAME -> coinList.data.sortedBy { it.coinInfo?.name }
                SortOptions.NAME_REVERSED -> coinList.data.sortedBy { it.coinInfo?.name }.reversed()
            }

    }.stateIn(screenModelScope, SharingStarted.Lazily, emptyList())

    fun getAllCoinList() {
        screenModelScope.launch {
            coinApi.getAllCoinList().collectLatest {
                _coinList.value = it
            }
        }
    }

    fun onSearchQueryChange(text: String) {
        _searchText.value = text
    }

    fun setSortOption(sortOption: SortOptions) {
        _sortingOption.value = sortOption
    }

}