package com.akcay.cryptokmm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.akcay.cryptokmm.network.entities.response.CoinInfo
import com.akcay.cryptokmm.network.entities.response.CoinNewsModelItem
import com.akcay.cryptokmm.network.entities.response.DISPLAY
import com.seiko.imageloader.rememberImagePainter

@Composable
fun CoinNewsItemView(news: CoinNewsModelItem) {
    Box(
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
            .padding(start = 20.dp, bottom = 30.dp, end = 20.dp).clip(
                RoundedCornerShape(10.dp)
            ).background(Color(0xF2F2F2))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberImagePainter(news.img ?: "")
            Image(
                painter,
                contentDescription = null,
                modifier = Modifier.width(40.dp).height(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = news.name ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
        }
    }

}