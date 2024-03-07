package com.anbui.skribbl.feature.selectRoom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.anbui.skribbl.core.presentation.component.SkribblColumn
import com.anbui.skribbl.core.presentation.component.SkribblTextField
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import com.anbui.skribbl.feature.createRoom.CreateRoomScreen
import com.anbui.skribbl.feature.selectRoom.components.RoomItem
import org.koin.compose.koinInject

class SelectRoomScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel: SelectRoomScreenModel = koinInject()
        val navigator = LocalNavigator.currentOrThrow

        val roomQuery by screenModel.roomQuery.collectAsState()
        val rooms by screenModel.room.collectAsState()

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
                    onValueChange = screenModel::changeRoomQuery,
                    hint = "Search for room"
                )
                IconButton(
                    onClick = screenModel::searchRoom
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null)
                }
            }
            LazyColumn(
                modifier = Modifier.padding(horizontal = SpaceMedium),
                verticalArrangement = Arrangement.spacedBy(SpaceMedium)
            ) {
                items(rooms) {
                    RoomItem(
                        room = it,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("or")
                Text("Create new room",
                    modifier = Modifier.clickable {
                        navigator.push(CreateRoomScreen())
                    }
                )
            }
        }
    }
}