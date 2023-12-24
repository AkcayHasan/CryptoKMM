package com.akcay.cryptokmm.ui.screens.homescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.currentOrThrow
import com.akcay.cryptokmm.ui.components.CoinListItemView
import com.akcay.cryptokmm.ui.components.CustomSearchBar
import com.akcay.cryptokmm.ui.screens.detailscreen.DetailScreen
import com.akcay.cryptokmm.ui.utils.LocalAppNavigator
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    screenModel: HomeScreenViewModel = koinInject()
) {

    screenModel.getAllCoinList()

    val coinList by screenModel.coinList.collectAsState()
    val searchText by screenModel.searchText.collectAsState()

    screenModel.setSortOption(SortOptions.DEFAULT)
    val navigator = LocalAppNavigator.currentOrThrow

    Scaffold() {
        Column() {
            Spacer(modifier = Modifier.height(10.dp))
            CustomSearchBar(modifier = Modifier, searchText, screenModel::onSearchQueryChange)
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
                Demo_DropDownMenu {
                    screenModel.setSortOption(it)
                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(coinList) { item ->
                    item.coinInfo?.let {
                        CoinListItemView(it, item.display) {
                            navigator.push(DetailScreen(item))
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun Demo_DropDownMenu(
    onClick: (SortOptions) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Default") },
                onClick = {
                    onClick.invoke(SortOptions.DEFAULT)
                    expanded = !expanded
                }
            )
            DropdownMenuItem(
                text = { Text("Price (Low-High)") },
                onClick = {
                    onClick.invoke(SortOptions.PRICE)
                    expanded = !expanded
                }
            )
            DropdownMenuItem(
                text = { Text("Price (High-Low)") },
                onClick = {
                    onClick.invoke(SortOptions.PRICE_REVERSED)
                    expanded = !expanded
                }
            )
            DropdownMenuItem(
                text = { Text("Name (A-Z)") },
                onClick = {
                    onClick.invoke(SortOptions.NAME)
                    expanded = !expanded
                }
            )
            DropdownMenuItem(
                text = { Text("Name (Z-A)") },
                onClick = {
                    onClick.invoke(SortOptions.NAME_REVERSED)
                    expanded = !expanded
                }
            )
        }
    }
}