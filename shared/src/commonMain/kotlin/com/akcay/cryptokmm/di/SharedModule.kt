package com.akcay.cryptokmm.di

import com.akcay.cryptokmm.database.CryptoDatabase
import com.akcay.cryptokmm.db.DatabaseDriverFactory
import com.akcay.cryptokmm.db.idAdapter
import com.akcay.cryptokmm.network.api.CoinApi
import com.akcay.cryptokmm.network.api.CoinApiImpl
import com.akcay.cryptokmm.ui.screens.detailscreen.DetailScreenViewModel
import com.akcay.cryptokmm.network.repository.CryptoRepository
import com.akcay.cryptokmm.network.repository.CryptoRepositoryImpl
import com.akcay.cryptokmm.ui.screens.favouritescreen.FavouriteScreenViewModel
import com.akcay.cryptokmm.ui.screens.homescreen.HomeScreenViewModel
import com.akcay.cryptokmm.ui.screens.settingsscreen.SettingsScreenViewModel
import database.CryptoEntity
import org.koin.core.module.Module
import org.koin.dsl.module

fun sharedModule() = module {

    single {
        CryptoDatabase(
            driver = get<DatabaseDriverFactory>().createDriver(),
            cryptoEntityAdapter = CryptoEntity.Adapter(
                idAdapter = idAdapter
            )
        )
    }

    factory<CoinApi> {
        CoinApiImpl()
    }

    single<CryptoRepository> {
        CryptoRepositoryImpl(database = get())
    }

    single {
        HomeScreenViewModel(
            coinApi = get(),
            cryptoRepository = get()
        )
    }

    single {
        DetailScreenViewModel(coinApi = get())
    }

    single {
        FavouriteScreenViewModel(cryptoRepository = get())
    }

    single {
        FavouriteScreenViewModel(cryptoRepository = get())
    }

    single {
        SettingsScreenViewModel(cryptoRepository = get())
    }
}

expect fun platformModule(): Module