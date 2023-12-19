package com.akcay.cryptokmm.di

import com.akcay.cryptokmm.ui.screens.favouritescreen.FavouriteScreenViewModel
import com.akcay.cryptokmm.ui.screens.homescreen.HomeScreenViewModel
import com.akcay.cryptokmm.ui.screens.settingsscreen.SettingsScreenViewModel
import org.koin.dsl.module

fun sharedModule() = module {
    single {
        HomeScreenViewModel()
    }

    single {
        FavouriteScreenViewModel()
    }

    single {
        SettingsScreenViewModel()
    }
}