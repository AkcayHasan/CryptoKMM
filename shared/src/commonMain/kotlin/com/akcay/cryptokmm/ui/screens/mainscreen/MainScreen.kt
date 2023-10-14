package com.akcay.cryptokmm.ui.screens.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.akcay.cryptokmm.network.entities.response.CoinListModel
import com.akcay.cryptokmm.ui.components.CoinListItemView

@Composable
fun MainScreen() {
    val screens = listOf("Home", "Favourite", "Settings")
    var selectedScreen by remember { mutableStateOf(screens.first()) }

    val itemViews = listOf(
        CoinListModel("", ""),
        CoinListModel("", ""),
        CoinListModel("", ""),
        CoinListModel("", "")
    )

    Scaffold(bottomBar = {
        BottomNavigation(backgroundColor = Color.White) {
            screens.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(getIconForScreen(screen), contentDescription = screen) },
                    selected = screen == selectedScreen,
                    onClick = { selectedScreen = screen },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }) {
        Column() {
            SearchBar()
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Most Popular")
                Icon(Icons.Filled.Home, contentDescription = null)
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(itemViews) { item: CoinListModel ->
                    CoinListItemView(coinListModel = item)
                }
            }

        }

    }


}

@Composable
fun getIconForScreen(screen: String): ImageVector {
    return when (screen) {
        "Home" -> Icons.Default.Home
        "Favourite" -> Icons.Default.Favorite
        "Settings" -> Icons.Default.Settings
        else -> Icons.Default.Home
    }
}


@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth()
    )
}
