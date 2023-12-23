package com.akcay.cryptokmm.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.akcay.cryptokmm.network.entities.response.CoinInfo

sealed class Screens {

    data class DetailsScreen(val item: CoinInfo) : Screen {

        @Composable
        override fun Content() {
            DetailsScreen(item)
        }
    }

    //    object DetailsScreen: Screen {
//
//        @Composable
//        override fun Content() {
//            DetailScreen()
//        }
//
//    }

}