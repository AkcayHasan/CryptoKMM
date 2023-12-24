package com.akcay.cryptokmm.ui.screens.homescreen

import CryptoKMM.shared.MR
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akcay.cryptokmm.ui.components.CoinListItemView
import com.akcay.cryptokmm.ui.components.CustomSearchBar
import dev.icerock.moko.resources.compose.painterResource
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    screenModel: HomeScreenViewModel = koinInject()
) {

    screenModel.getAllCoinList()

    val coinList by screenModel.coinList.collectAsState()
    val searchText by screenModel.searchText.collectAsState()

    Scaffold() {
        Column() {
            Spacer(modifier = Modifier.height(10.dp))
            CustomSearchBar(modifier = Modifier, searchText, screenModel::onSearchQueryChange)
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
                        CoinListItemView(it, item.display) {
                            screenModel.addCoinToDatabase(it)
                        }
                    }
                }
            }

        }
    }
}
