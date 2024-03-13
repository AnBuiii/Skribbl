package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import arrow.core.Either
import com.anbui.skribbl.core.data.remote.response.message.Announcement
import com.anbui.skribbl.core.data.remote.response.message.ChatMessage
import com.anbui.skribbl.core.presentation.theme.Color

@Composable
fun ChatSection(
    modifier: Modifier,
    chatMessages: List<Either<ChatMessage, Announcement>>,
    onOpenPlayerDrawer: () -> Unit,
    onVoice: () -> Unit,
    playerName: String
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
            modifier = Modifier.background(Color.Yellow),
            reverseLayout = true,

            ) {
            items(
                chatMessages,
                key = { chat ->
                    chat.fold(
                        ifLeft = { it.timeStamp },
                        ifRight = { it.timeStamp }
                    )
                }
            ) { chat ->
                when (chat) {
                    is Either.Left -> {
                        if (chat.value.roomName == playerName) {
                            MyChatItem(chatMessage = chat.value)
                        } else {
                            ChatItem(chatMessage = chat.value, modifier = Modifier.fillMaxWidth())
                        }
                    }

                    is Either.Right -> {
                        AnnouncementItem(announcement = chat.value)
                    }
                }
            }
        }
    }
}
