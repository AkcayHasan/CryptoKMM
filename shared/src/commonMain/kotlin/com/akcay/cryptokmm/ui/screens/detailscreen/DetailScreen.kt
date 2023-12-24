package com.akcay.cryptokmm.ui.screens.detailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import com.akcay.cryptokmm.network.entities.response.Data
import com.akcay.cryptokmm.ui.components.BASE_URL
import com.akcay.cryptokmm.ui.components.CoinNewsItemView
import com.akcay.cryptokmm.ui.utils.LocalAppNavigator
import com.seiko.imageloader.rememberImagePainter
import org.koin.compose.rememberKoinInject

@OptIn(ExperimentalMaterial3Api::class)
data class DetailScreen(
    val coin: Data
) : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberKoinInject<DetailScreenViewModel>()
        val navigator = LocalAppNavigator.currentOrThrow

        screenModel.getCoinNews()
        val coinNews by screenModel.coinNews.collectAsState()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.Black,
                    ),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            tint = Color.Black,
                            contentDescription = "text",
                            modifier = Modifier
                                .padding(2.dp, 0.dp)
                                .size(24.dp).clickable {
                                    navigator.pop()
                                }
                        )
                    },
                    title = {
                        Text(coin.coinInfo?.fullName ?: "", fontWeight = FontWeight.Black)
                    },
                    actions = {
                        IconButton(onClick = {
                            screenModel.addCoinToDatabase(coin)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                tint = Color.LightGray,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val painter = rememberImagePainter("$BASE_URL${coin.coinInfo?.imageUrl}")
                    Image(painter, contentDescription = null, modifier = Modifier.size(80.dp))
                    Text(
                        coin.display?.usd?.price ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                    Text(coin.display?.usd?.highHour ?: "", fontSize = 15.sp)
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(text = "News", modifier = Modifier.padding(20.dp))
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(coinNews) { item ->
                            CoinNewsItemView(item)
                        }
                    }
                }
            }
        }
    }


}