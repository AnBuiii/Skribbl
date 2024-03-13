package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anbui.skribbl.core.data.remote.response.message.Announcement
import com.anbui.skribbl.core.data.remote.response.message.ChatMessage
import com.anbui.skribbl.core.presentation.theme.Color
import com.anbui.skribbl.core.presentation.theme.SpaceSmall

@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    chatMessage: ChatMessage
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Start)
    ) {
        Box(
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.Black,
                shape = MaterialTheme.shapes.large
            ).background(Color.Yellow).padding(SpaceSmall).weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(chatMessage.message, style = MaterialTheme.typography.bodyMedium)
        }
        Text(chatMessage.from, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun AnnouncementItem(
    modifier: Modifier = Modifier,
    announcement: Announcement
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.background(Color.Yellow).padding(SpaceSmall).weight(1f)
                .widthIn(min = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(announcement.message, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun MyChatItem(
    modifier: Modifier = Modifier,
    chatMessage: ChatMessage
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.Black,
                shape = MaterialTheme.shapes.large
            ).background(Color.Yellow).padding(SpaceSmall).weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(chatMessage.message, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

