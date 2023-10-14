package com.akcay.cryptokmm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akcay.cryptokmm.network.entities.response.CoinListModel

@Composable
fun CoinListItemView(coinListModel: CoinListModel) {
    Box(
        modifier = Modifier.fillMaxWidth().height(50.dp)
            .padding(start = 10.dp, top = 10.dp, end = 10.dp).clip(
            RoundedCornerShape(10.dp)
        ).background(Color.Blue)
    ) {
        Row() {
            //Icon(),
            Row {
                Column {

                }
                Column {

                }
            }
        }
    }

}