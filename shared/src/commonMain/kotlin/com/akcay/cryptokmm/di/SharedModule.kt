package com.akcay.cryptokmm.di

import com.akcay.cryptokmm.network.api.CoinApi
import com.akcay.cryptokmm.network.api.CoinApiImpl
import com.akcay.cryptokmm.ui.screens.favouritescreen.FavouriteScreenViewModel
import com.akcay.cryptokmm.ui.screens.homescreen.HomeScreenViewModel
import com.akcay.cryptokmm.ui.screens.settingsscreen.SettingsScreenViewModel
import org.koin.dsl.module

fun sharedModule() = module {

    factory<CoinApi> {
        CoinApiImpl()
    }

    single {
        HomeScreenViewModel(coinApi = get())
    }

    single {
        FavouriteScreenViewModel()
    }

    single {
        FavouriteScreenViewModel()
    }

    single {
        SettingsScreenViewModel()
    }
}