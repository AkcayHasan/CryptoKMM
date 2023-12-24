package com.akcay.cryptokmm

import CryptoKMM.shared.MR
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.akcay.cryptokmm.database.CryptoDatabase
import com.akcay.cryptokmm.network.api.CoinApiImpl
import com.akcay.cryptokmm.network.repository.CryptoRepositoryImpl
import com.akcay.cryptokmm.ui.screens.favouritescreen.FavouriteScreen
import com.akcay.cryptokmm.ui.screens.favouritescreen.FavouriteScreenViewModel
import com.akcay.cryptokmm.ui.screens.homescreen.HomeScreen
import com.akcay.cryptokmm.ui.screens.homescreen.HomeScreenViewModel
import com.akcay.cryptokmm.ui.screens.settingsscreen.SettingsScreen
import com.akcay.cryptokmm.ui.screens.settingsscreen.SettingsScreenViewModel
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.getKoin

@Preview
@Composable
fun DraftForPreview() {
    SettingsScreen()
}