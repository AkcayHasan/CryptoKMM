package com.akcay.cryptokmm

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.akcay.cryptokmm.ui.screens.mainscreen.MainScreen
import com.akcay.cryptokmm.ui.utils.ProvideAppNavigator
import org.koin.compose.KoinContext

@Composable
fun App() {

    KoinContext {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigator(
                screen = MainScreen(),
            ) {
                ProvideAppNavigator(it) {
                    SlideTransition(navigator = it)
                }
            }
        }
    }
}