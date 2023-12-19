package com.akcay.cryptokmm.android

import android.app.Application
import com.akcay.cryptokmm.di.KoinInit
import org.koin.android.ext.koin.androidContext

class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInit().init {
            androidContext(androidContext = this@AndroidApp)
            modules(
                listOf()
            )
        }
    }

}