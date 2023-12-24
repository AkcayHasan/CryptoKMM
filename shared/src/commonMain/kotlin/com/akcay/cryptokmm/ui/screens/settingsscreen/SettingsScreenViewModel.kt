package com.akcay.cryptokmm.ui.screens.settingsscreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.akcay.cryptokmm.network.repository.CryptoRepository
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    private val cryptoRepository: CryptoRepository
) : ScreenModel {


    fun deleteAllCoins() {
        screenModelScope.launch {
            cryptoRepository.deleteAllCoins()
        }
    }

}