package com.akcay.cryptokmm.ui.components

import CryptoKMM.shared.MR
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    text: String = stringResource(MR.strings.search_bar_hint_text),
    onQueryChange: (String) -> Unit
) {

    Card(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(MR.colors.componentGray))
    ) {
        TextField(
            modifier = modifier,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(MR.colors.componentGray),
                unfocusedContainerColor = colorResource(MR.colors.componentGray),
                unfocusedPlaceholderColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            value = text,
            onValueChange = onQueryChange,
            placeholder = { Text("Search") },
            shape = RoundedCornerShape(10.dp),
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) }
        )
    }

}