package com.anbui.skribbl.feature.username

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.anbui.skribbl.core.presentation.component.SkribblColumn
import com.anbui.skribbl.core.presentation.component.SkribblTextButton
import com.anbui.skribbl.core.presentation.component.SkribblTextField
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import com.anbui.skribbl.feature.selectRoom.SelectRoomScreen
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.app_name

class UsernameScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: UsernameScreenModel = getScreenModel()
        val username by screenModel.username.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            screenModel.screenEvent.collect { event ->
                if (event == UsernameScreenEvent.GoNext) {
                    navigator.push(SelectRoomScreen())
                }
            }
        }

        UsernameScreenContent(
            username = username,
            onChangeUsername = screenModel::changeUsername,
            onNext = screenModel::next
        )
    }
}


@Composable
fun UsernameScreenContent(
    username: String,
    onChangeUsername: (String) -> Unit,
    onNext: () -> Unit
) {
    SkribblColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(Res.string.app_name),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Column {
            SkribblTextField(
                username,
                onValueChange = onChangeUsername,
                hint = "Choose username"
            )
            Spacer(modifier = Modifier.height(SpaceMedium))

            SkribblTextButton("Next", modifier = Modifier.align(Alignment.End), onClick = onNext)
        }
    }
}

@Preview
@Composable
fun UsernameScreenPreview() {
    UsernameScreenContent("asd", {}, {})
}