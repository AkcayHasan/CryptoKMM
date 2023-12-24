package com.akcay.cryptokmm.ui.screens.homescreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.akcay.cryptokmm.network.api.CoinApi
import com.akcay.cryptokmm.network.entities.response.CoinListModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val coinApi: CoinApi,

) : ScreenModel {
    private val _searchText = MutableStateFlow("")
    private val _sortingOption = MutableStateFlow(SortOptions.DEFAULT)
    val searchText = _searchText.asStateFlow()

    private val _coinList = MutableStateFlow(CoinListModel())

    @OptIn(FlowPreview::class)
    val coinList = combine(_coinList, _searchText.debounce(500L), _sortingOption) { coinList, searchText, sortingOption ->
        val list = coinList.data.filter {
            it.coinInfo?.fullName?.lowercase()?.contains(searchText.lowercase()) ?: false
        }
        when(sortingOption){
            SortOptions.DEFAULT -> list
            SortOptions.PRICE -> list.sortedBy { it.raw?.usd?.price }
            SortOptions.PRICE_REVERSED -> list.sortedBy { it.raw?.usd?.price }.reversed()
            SortOptions.NAME -> list.sortedBy { it.coinInfo?.name }
            SortOptions.NAME_REVERSED -> list.sortedBy { it.coinInfo?.name }.reversed()
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