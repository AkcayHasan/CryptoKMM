package com.akcay.cryptokmm.ui.screens.favouritescreen

import CryptoKMM.shared.MR
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akcay.cryptokmm.ui.components.CoinListItemView
import com.akcay.cryptokmm.ui.components.CustomSearchBar
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    screenModel: FavouriteScreenViewModel = koinInject()
) {

    val coinList by screenModel.coinTempList.collectAsState()
    val searchText by screenModel.searchText.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    stringResource(MR.strings.favourite_screen_title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        )
    }) {
        Column {
            CustomSearchBar(
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
                text = searchText,
                onQueryChange = screenModel::onSearchQueryChange
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(coinList) { item ->
                    item.coinInfo?.let {
                        CoinListItemView(it, item.display) {

                        }
                    }
                }
            }

        }

    }

}