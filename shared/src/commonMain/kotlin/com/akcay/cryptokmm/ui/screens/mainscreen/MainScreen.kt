package com.akcay.cryptokmm.ui.screens.mainscreen

import CryptoKMM.shared.MR
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akcay.cryptokmm.network.api.CoinApiImpl
import com.akcay.cryptokmm.ui.components.CoinListItemView
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun MainScreen() {
    val screens = listOf("Home", "Favourite", "Settings")
    var selectedScreen by remember { mutableStateOf(screens.first()) }
    val viewModel = remember { MainScreenViewModel(CoinApiImpl()) }

    viewModel.getAllCoinList()

    val coinList by viewModel.coinList.collectAsState()

    Scaffold(bottomBar = {
        NavigationBar(
            containerColor = Color.White,
            modifier = Modifier.clip(
                RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
        ) {
            screens.forEach { screen ->
                NavigationBarItem(
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
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Most Popular",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
                Icon(
                    painterResource(MR.images.filter_icon),
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(coinList.data) { item ->
                    item.coinInfo?.let {
                        CoinListItemView(it)
                    }
                }
            }

        }
    }
}

@Composable
fun getIconForScreen(screen: String): Painter {
    return when (screen) {
        "Home" -> painterResource(MR.images.home_icon)
        "Favourite" -> painterResource(MR.images.favourite_icon)
        "Settings" -> painterResource(MR.images.settings_icon)
        else -> painterResource(MR.images.home_icon)
    }
}


@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 20.dp, end = 20.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xF2F2F2),
            unfocusedContainerColor = Color(0xF2F2F2),
            unfocusedPlaceholderColor = Color.Gray,
            focusedPlaceholderColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Search") },
        shape = RoundedCornerShape(10.dp),
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) }
    )


}
