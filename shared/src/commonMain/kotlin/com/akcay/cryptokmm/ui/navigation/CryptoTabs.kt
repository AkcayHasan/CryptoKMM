package com.akcay.cryptokmm.ui.navigation

import CryptoKMM.shared.MR
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.akcay.cryptokmm.ui.screens.favouritescreen.FavouriteScreen
import com.akcay.cryptokmm.ui.screens.homescreen.HomeScreen
import com.akcay.cryptokmm.ui.screens.settingsscreen.SettingsScreen
import dev.icerock.moko.resources.compose.painterResource

sealed class CryptoTabs {

    object HomeTab : Tab {

        @Composable
        override fun Content() {
            HomeScreen()
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = "Home"
                val icon = painterResource(MR.images.home_icon)

                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon
                    )
                }
            }

    }

    object FavouriteTab : Tab {

        @Composable
        override fun Content() {
            FavouriteScreen()
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = "Favourite"
                val icon = painterResource(MR.images.favourite_icon)

                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon
                    )
                }
            }

    }

    object SettingsTab : Tab {

        @Composable
        override fun Content() {
            SettingsScreen()
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = "Settings"
                val icon = painterResource(MR.images.settings_icon)

                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon
                    )
                }
            }

    }
}
