package com.anbui.skribbl.feature.selectRoom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.anbui.skribbl.core.presentation.component.SkribblColumn
import com.anbui.skribbl.core.presentation.component.SkribblTextField
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import com.anbui.skribbl.core.presentation.theme.debounceClickable
import com.anbui.skribbl.domain.model.Room
import com.anbui.skribbl.feature.createRoom.CreateRoomScreen
import com.anbui.skribbl.feature.game.GameScreen
import com.anbui.skribbl.feature.selectRoom.components.RoomItem
import org.jetbrains.compose.resources.stringResource
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.create_room
import skribbl.composeapp.generated.resources.no_rooms_found
import skribbl.composeapp.generated.resources.search_for_rooms

class SelectRoomScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel: SelectRoomScreenModel = getScreenModel()
        val navigator = LocalNavigator.currentOrThrow

        val roomQuery by screenModel.roomQuery.collectAsState()
        val rooms by screenModel.room.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.screenEvent.collect { event ->
                if (event == ScreenEvent.GoNext) {
                    navigator.push(GameScreen())
                }
            }
        }

        SelectRoomContent(
            rooms = rooms,
            roomQuery = roomQuery,
            onRoomQueryChange = screenModel::changeRoomQuery,
            onClickCreateRoom = { navigator.push(CreateRoomScreen()) },
            onClickSearchRoom = screenModel::searchRoom,
            onJoinRoom = screenModel::joinRoom,
        )
    }
}

@Composable
fun SelectRoomContent(
    rooms: List<Room>,
    roomQuery: String,
    onRoomQueryChange: (String) -> Unit,
    onClickSearchRoom: () -> Unit,
    onJoinRoom: (roomName: String) -> Unit,
    onClickCreateRoom: () -> Unit
) {
    val roomsEmpty by derivedStateOf { rooms.isEmpty() }

    SkribblColumn(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(SpaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SkribblTextField(
                modifier = Modifier.weight(1f),
                text = roomQuery,
                onValueChange = onRoomQueryChange,
                hint = stringResource(Res.string.search_for_rooms)
            )
            IconButton(
                onClick = onClickSearchRoom,
            ) {
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier.padding(horizontal = SpaceMedium),
            verticalArrangement = Arrangement.spacedBy(SpaceMedium)
        ) {
            items(rooms, key = { it.roomName }) { room ->
                RoomItem(
                    room = room,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onJoinRoom(room.roomName)
                    }
                )
            }
            if (roomsEmpty) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            stringResource(Res.string.no_rooms_found),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("or", style = MaterialTheme.typography.bodyMedium)
            Text(
                stringResource(Res.string.create_room).uppercase(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.debounceClickable {
                    onClickCreateRoom()
                }
            )
        }
    }
}
