package com.anbui.skribbl.feeature.start

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.ScreenModel
import com.anbui.skribbl.core.utils.Greeting
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.app_name
import skribbl.composeapp.generated.resources.compose_multiplatform

@Composable
fun StartScreen(
    screenModel: ScreenModel = koinInject()
) {
    var showContent by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { showContent = !showContent }) {
            Text(stringResource(Res.string.app_name))
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text(
                    "Compose: $greeting",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}