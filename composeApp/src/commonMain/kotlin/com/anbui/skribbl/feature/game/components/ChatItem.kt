package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.anbui.skribbl.core.data.remote.response.message.Announcement
import com.anbui.skribbl.core.data.remote.response.message.ChatMessage
import com.anbui.skribbl.core.presentation.theme.SkribblColor
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import com.anbui.skribbl.core.presentation.theme.SpaceSmall
import com.anbui.skribbl.core.presentation.theme.drawChat
import com.anbui.skribbl.core.utils.toLocalTimeString

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
        Column(
            modifier = Modifier.weight(1f).drawChat().padding(SpaceMedium),
        ) {
            Text(chatMessage.from, style = MaterialTheme.typography.bodyLarge)
            Text(chatMessage.message, style = MaterialTheme.typography.bodyMedium)
        }
        Box(modifier = Modifier.width(120.dp)) {
            Text(chatMessage.timeStamp.toLocalTimeString())
        }
    }
}

@Composable
fun AnnouncementItem(
    modifier: Modifier = Modifier,
    announcement: Announcement
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Start)
    ) {
        Box(
            modifier = Modifier.weight(1f).drawChat(Color.Green).padding(SpaceMedium),
        ) {
            Text(announcement.message, style = MaterialTheme.typography.bodyMedium)
        }
        Box(modifier = Modifier.width(120.dp)) {
            Text(announcement.timeStamp.toLocalTimeString())
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
                color = SkribblColor.Black,
                shape = MaterialTheme.shapes.large
            ).background(SkribblColor.Yellow).padding(SpaceSmall).weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(chatMessage.message, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

