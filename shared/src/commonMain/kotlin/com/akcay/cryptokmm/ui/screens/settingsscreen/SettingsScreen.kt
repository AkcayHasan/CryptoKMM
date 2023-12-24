package com.akcay.cryptokmm.ui.screens.settingsscreen

import CryptoKMM.shared.MR
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    screenModel: SettingsScreenViewModel = koinInject()
) {

    val checked = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        MinimalDialog(showDialog, screenModel)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    stringResource(MR.strings.settings_screen_title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        )
    }) {
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth().height(50.dp)
                    .background(
                        color = colorResource(MR.colors.componentGray),
                        shape = RoundedCornerShape(size = 10.dp)
                    ).padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Dark Mode")
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth().height(50.dp)
                    .background(
                        color = colorResource(MR.colors.componentGray),
                        shape = RoundedCornerShape(size = 10.dp)
                    ).padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Currency")
                Spacer(modifier = Modifier.weight(1f))
                Text("USD")
                Icon(
                    modifier = Modifier.size(15.dp),
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "When you select a new base currency, all prices in the app will be displayed in that currency.",
                color = colorResource(MR.colors.textGray),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(size = 15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(MR.colors.buttonRed)),
                onClick = {
                    showDialog.value = true
                }) {
                Text("Remove All Data", color = Color.White)
            }
        }

    }

}

@Composable
fun MinimalDialog(showDialog: MutableState<Boolean>, screenModel: SettingsScreenViewModel) {
    Dialog(onDismissRequest = { showDialog.value = false }) {
        Card(
            modifier = Modifier.width(300.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                Text(
                    text = "This will delete all the saved favorites, are you sure?",
                    modifier = Modifier.fillMaxWidth().padding(all = 15.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp
                )
                Button(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(size = 15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(MR.colors.buttonRed)),
                    onClick = {
                        showDialog.value = false
                        screenModel.deleteAllCoins()
                    }) {
                    Text("Delete", color = Color.White)
                }
            }
        }
    }
}