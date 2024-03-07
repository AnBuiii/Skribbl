package com.anbui.skribbl.feature.username

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.anbui.skribbl.core.presentation.component.SkribblTextButton
import com.anbui.skribbl.core.presentation.component.SkribblTextField
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import com.anbui.skribbl.feature.selectRoom.SelectRoomScreen
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.app_name

class UsernameScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: UsernameScreenModel = koinInject()
        val navigator = LocalNavigator.currentOrThrow

        val username by screenModel.username.collectAsState()

        val localFocusManager = LocalFocusManager.current
        val success by screenModel.success.collectAsState()

        LaunchedEffect(success) {
            navigator.push(SelectRoomScreen())
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
            Text(stringResource(Res.string.app_name), fontSize = 32.sp)

            Spacer(
                modifier = Modifier.height(64.dp)
            )

            Column(

            ) {

                SkribblTextField(
                    username,
                    onValueChange = screenModel::changeUsername,
                    hint = "Choose username"
                )
                Spacer(modifier = Modifier.height(SpaceMedium))

                SkribblTextButton("Next", modifier = Modifier.align(Alignment.End)) {
                    screenModel.next()
                }
            }
        }
    }

}