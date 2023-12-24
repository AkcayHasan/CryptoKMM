package com.akcay.cryptokmm.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun PercentageView(percentage: String?, highHour: String?, fontSize: TextUnit) {
    val isRised = (percentage?.toDoubleOrNull() ?: 0.0) >= 0.0
    return Text(text = "$percentage% ($highHour)", color = if (isRised) Color.Green else Color.Red, fontSize = fontSize)
}

@Composable
fun PercentageViewWithIcon(percentage: String?, fontSize: TextUnit) {
    val isRised = (percentage?.toDoubleOrNull() ?: 0.0) >= 0.0

    return Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.LightGray
    ) {
        Row {
            Icon(
                imageVector = if(isRised) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                tint = if (isRised) Color.Green else Color.Red,
                contentDescription = "text",
                modifier = Modifier.padding(2.dp)
            )
            Text(text = "%$percentage", color = if (isRised) Color.Green else Color.Red, fontWeight = FontWeight.Bold, fontSize = fontSize, modifier = Modifier.padding(8.dp, 2.dp))
        }
    }
}