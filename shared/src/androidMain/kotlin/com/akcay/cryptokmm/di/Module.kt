package com.akcay.cryptokmm.di

import com.akcay.cryptokmm.db.DatabaseDriverFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory(context = get()) }
}