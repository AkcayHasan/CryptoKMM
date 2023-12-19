package com.akcay.cryptokmm.ui.screens.mainscreen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.akcay.cryptokmm.ui.navigation.CryptoTabs

class NewMainScreen : Screen {

    @Composable
    override fun Content() {
        TabNavigator(
            CryptoTabs.HomeTab
        ) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = MaterialTheme.colorScheme.background
                    ) {
                        TabNavigationItem(CryptoTabs.HomeTab)
                        TabNavigationItem(CryptoTabs.FavouriteTab)
                        TabNavigationItem(CryptoTabs.SettingsTab)
                    }
                }
            ) {
                CurrentScreen()
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = if (isSelected) {
                        it
                    } else {
                        it
                    },
                    contentDescription = tab.options.title,
                    tint = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    },
                )
            }
        },
    )
}