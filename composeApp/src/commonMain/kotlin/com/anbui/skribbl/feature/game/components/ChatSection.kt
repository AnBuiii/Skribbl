package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anbui.skribbl.core.presentation.theme.Color

@Composable
fun ChatSection(
    modifier: Modifier,
    onOpenPlayerDrawer: () -> Unit,
    onVoice: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.background)
    ) {
        Column {
            IconButton(
                onClick = onOpenPlayerDrawer
            ) {
                Icon(Icons.Default.Person, "")
            }
            IconButton(
                onClick = onVoice
            ) {
                Icon(Icons.Default.Call, "")
            }
        }
        LazyColumn(
            modifier = Modifier.background(Color.Yellow)
        )
        {
            items(100) {
                AnnouncementItem("asdasdasdasd")
            }
        }
    }
}