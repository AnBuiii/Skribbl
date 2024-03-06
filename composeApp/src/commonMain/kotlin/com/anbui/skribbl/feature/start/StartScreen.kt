package com.anbui.skribbl.feature.start

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.anbui.skribbl.core.utils.Greeting
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.app_name
import skribbl.composeapp.generated.resources.compose_multiplatform

@Composable
fun StartScreen(
    screenModel: StartScreenModel = koinInject()
) {
    val showContent by screenModel.showContent.collectAsState()
    val name by screenModel.name.collectAsState()

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {
            screenModel.toggle()
            Napier.v("Toggle")
        }
        ) {
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
                    "$name: $greeting",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}