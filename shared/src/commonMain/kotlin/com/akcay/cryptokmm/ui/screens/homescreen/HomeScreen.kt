package com.akcay.cryptokmm.ui.screens.homescreen

import CryptoKMM.shared.MR
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akcay.cryptokmm.ui.components.CoinListItemView
import dev.icerock.moko.resources.compose.painterResource
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    screenModel: HomeScreenViewModel = koinInject()
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home Screen")
    }

    screenModel.getAllCoinList()

    val coinList by screenModel.coinList.collectAsState()
    val searchText by screenModel.searchText.collectAsState()

    Scaffold() {
        Column() {
            SearchBar(searchText, screenModel::onSearchQueryChange)
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                androidx.compose.material3.Text(
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
                items(coinList) { item ->
                    item.coinInfo?.let {
                        CoinListItemView(it, item.display)
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
fun SearchBar(text: String, onQueryChange: (String) -> Unit) {

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
        onValueChange = onQueryChange,
        placeholder = { androidx.compose.material3.Text("Search") },
        shape = RoundedCornerShape(10.dp),
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) }
    )

}
