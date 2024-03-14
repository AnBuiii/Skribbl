package com.anbui.skribbl.feature.createRoom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.anbui.skribbl.core.presentation.component.SkribblColumn
import com.anbui.skribbl.core.presentation.component.SkribblTextButton
import com.anbui.skribbl.core.presentation.component.SkribblTextField
import com.anbui.skribbl.core.presentation.theme.SpaceLarge
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import org.jetbrains.compose.resources.stringResource
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.create_a_new_room
import skribbl.composeapp.generated.resources.create_room
import skribbl.composeapp.generated.resources.room_name
import skribbl.composeapp.generated.resources.room_size

class CreateRoomScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: CreateRoomScreenModel = getScreenModel()
        val navigator = LocalNavigator.currentOrThrow

        val roomName by screenModel.roomName.collectAsState()
        val roomSize by screenModel.roomSize.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.screenEvent.collect { event ->
                if (event is CreateRoomScreenEvent.GoNext) {
                    navigator.pop()
                }
            }
        }

        CreateRoomScreenContent(
            roomName = roomName,
            roomSize = roomSize,
            onChangeRoomName = screenModel::changeRoomName,
            onChangeRoomSize = screenModel::changeRoomSize,
            onCreateRoom = screenModel::createRoom
        )
    }
}

@Composable
fun CreateRoomScreenContent(
    roomName: String,
    roomSize: String,
    onChangeRoomName: (String) -> Unit,
    onChangeRoomSize: (String) -> Unit,
    onCreateRoom: () -> Unit
) {
    SkribblColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(Res.string.create_a_new_room),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(SpaceLarge))
        Column(
            modifier = Modifier.fillMaxWidth().padding(SpaceLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SpaceMedium)
        ) {
            SkribblTextField(
                modifier = Modifier.fillMaxWidth(),
                text = roomName,
                onValueChange = onChangeRoomName,
                hint = stringResource(Res.string.room_name)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SkribblTextField(
                    modifier = Modifier.width(120.dp),
                    text = roomSize,
                    onValueChange = onChangeRoomSize,
                    hint = stringResource(Res.string.room_size),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                SkribblTextButton(
                    text = stringResource(Res.string.create_room).uppercase(),
                    onClick = onCreateRoom,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
