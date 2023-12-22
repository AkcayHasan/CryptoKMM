package com.akcay.cryptokmm.di

import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class KoinInit {

    fun init(appDeclaration: KoinAppDeclaration = {}): Koin {
        return startKoin {
            modules(
                listOf(
                    sharedModule(),
                    networkModule()
                )
            )
            appDeclaration()
        }.koin
    }
}