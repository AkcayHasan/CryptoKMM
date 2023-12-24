package com.akcay.cryptokmm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akcay.cryptokmm.network.entities.response.Data
import com.seiko.imageloader.rememberImagePainter

var BASE_URL = "https://www.cryptocompare.com"

@Composable
fun CoinListItemView(coin: Data?, onClickItem: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 20.dp, bottom = 10.dp, end = 20.dp).clip(
                RoundedCornerShape(10.dp)
            ).background(Color(0xF2F2F2)).clickable {
                onClickItem.invoke()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberImagePainter("$BASE_URL${coin?.coinInfo?.imageUrl}")
            Image(
                painter,
                contentDescription = null,
                modifier = Modifier.width(40.dp).height(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Row(
                modifier = Modifier.fillMaxSize().padding(top = 10.dp, end = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(text = coin?.coinInfo?.name ?: "", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = coin?.coinInfo?.fullName ?: "", fontSize = 13.sp)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = coin?.display?.usd?.price ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    PercentageView(
                        coin?.display?.usd?.changePercentageHour,
                        coin?.display?.usd?.highHour,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }

}