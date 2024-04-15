package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anbui.skribbl.core.data.remote.response.message.PlayerData

@Composable
fun PlayerDrawerSheet(
    players: List<PlayerData>
) {
    ModalDrawerSheet {
        Text("Drawer title", modifier = Modifier.padding(16.dp))
        Divider()
        players.sortedBy { it.rank }.forEachIndexed { idx, it ->
            NavigationDrawerItem(
                label = { Text(text = it.username + " " + it.score) },
                selected = false,
                onClick = { /*TODO*/ }
            )
        }
    }
}
