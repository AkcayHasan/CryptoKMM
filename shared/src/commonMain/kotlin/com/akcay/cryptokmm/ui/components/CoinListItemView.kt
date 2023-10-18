package com.akcay.cryptokmm.ui.components

import CryptoKMM.shared.MR
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akcay.cryptokmm.network.entities.response.CoinInfo
import dev.icerock.moko.resources.compose.painterResource
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url

@Composable
fun CoinListItemView(coinInfo: CoinInfo) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 20.dp, bottom = 10.dp, end = 20.dp).clip(
                RoundedCornerShape(10.dp)
            ).background(Color(0xF2F2F2))
    ) {
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            asyncPainterResource(data = Url(coinInfo.imageUrl ?: ""))
            Spacer(modifier = Modifier.width(10.dp))
            Row(modifier = Modifier.fillMaxSize().padding(top = 10.dp, end = 10.dp, bottom = 10.dp),horizontalArrangement = Arrangement.SpaceBetween) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(text = coinInfo.name ?: "", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "dsadsa", fontSize = 13.sp)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "asdasdasdasd", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "dsadsa", fontSize = 13.sp)
                }
            }
        }
    }

}