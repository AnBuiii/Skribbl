package com.anbui.skribbl.feature.createRoom

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.anbui.skribbl.core.presentation.component.SkribblTextButton
import com.anbui.skribbl.core.presentation.component.SkribblTextField
import com.anbui.skribbl.core.presentation.theme.SpaceLarge
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import com.anbui.skribbl.feature.game.GameScreen
import com.anbui.skribbl.feature.selectRoom.ScreenState
import org.koin.compose.koinInject

class CreateRoomScreen : Screen {
    @Composable
    override fun Content() {
        val localFocusManager = LocalFocusManager.current
        val screenModel: CreateRoomScreenModel = koinInject()
        val navigator = LocalNavigator.currentOrThrow

        val roomName by screenModel.roomName.collectAsState()
        val roomSize by screenModel.roomSize.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.screenState.collect { screenState ->
                if (screenState is ScreenState.DONE) {
                    navigator.pop()
                }
            }
        }

        Column(
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text("Create new room")
            Column(
                modifier = Modifier.fillMaxWidth().padding(SpaceLarge),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(SpaceMedium)
            ) {
                SkribblTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = roomName,
                    onValueChange = screenModel::changeRoomName
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SkribblTextField(
                        modifier = Modifier.weight(1f),
                        text = roomSize,
                        onValueChange = screenModel::changeRoomSize
                    )

                    SkribblTextButton("Create") {
                        screenModel.createRoom()
                    }
                }
            }
        }
    }
}
