package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.anbui.skribbl.core.data.remote.response.message.Announcement
import com.anbui.skribbl.core.data.remote.response.message.ChatMessage
import com.anbui.skribbl.core.presentation.theme.Color
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import com.anbui.skribbl.core.presentation.theme.SpaceSmall
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

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
            modifier = Modifier.weight(1f)
                .drawBehind {
                    val path = Path()

                    val height = size.height
                    val width = size.width
                    val corner = 10f

                    path.apply {
                        lineTo(width - corner, 0f)
                        arcTo(
                            Rect(
                                topLeft = Offset(width - 2 * corner, 0f),
                                bottomRight = Offset(width, 0f + 2 * corner)
                            ),
                            forceMoveTo = false,
                            startAngleDegrees = -90f,
                            sweepAngleDegrees = 90f
                        )
                        lineTo(width, height - corner)
                        arcTo(
                            Rect(
                                topLeft = Offset(width - 2 * corner, height - 2 * corner),
                                bottomRight = Offset(width, height)
                            ),
                            forceMoveTo = false,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 90f
                        )
                        lineTo(0f + corner + corner, height)
                        arcTo(
                            Rect(
                                topLeft = Offset(0f + corner, height - 2 * corner),
                                bottomRight = Offset(0f + 3 * corner, height)
                            ),
                            forceMoveTo = false,
                            startAngleDegrees = 90f,
                            sweepAngleDegrees = 90f
                        )
                        lineTo(0f + corner, corner)
                        lineTo(0f, 0f)
                    }
                    drawPath(path, Color.Black, style = Stroke(2f))
                }.padding(SpaceMedium),
        ) {
            Text(chatMessage.from, style = MaterialTheme.typography.bodyLarge)
            Text(chatMessage.message, style = MaterialTheme.typography.bodyMedium)
        }
        Box(modifier = Modifier.width(120.dp)){
            Text(chatMessage.timeStamp.toLocalTimeString())
        }
    }
}

@OptIn(FormatStringsInDatetimeFormats::class)
fun Long.toLocalTimeString(): String {
    val instant = Instant.fromEpochMilliseconds(this)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val formatPattern = "HH:mm:ss"
    val formatter = LocalDateTime.Format { byUnicodePattern(formatPattern) }
    return formatter.format(localDateTime)
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

