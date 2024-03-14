package com.anbui.skribbl.feature.selectRoom.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anbui.skribbl.core.presentation.theme.Color
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import com.anbui.skribbl.core.presentation.theme.debounceClickable
import com.anbui.skribbl.domain.model.Room

@Composable
fun RoomItem(
    room: Room,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .debounceClickable { onClick() }
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = MaterialTheme.shapes.small
            ).padding(SpaceMedium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(room.roomName, style = MaterialTheme.typography.bodyLarge)

        Text("${room.playerCount}/${room.roomSize}", style = MaterialTheme.typography.bodyMedium)
    }
}
