package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anbui.skribbl.core.presentation.theme.Color
import com.anbui.skribbl.core.presentation.theme.SpaceSmall

@Composable
fun AnnouncementItem(
    message: String
) {
    Box(
        modifier = Modifier.border(
            width = 1.dp, color = Color.Black, shape = MaterialTheme.shapes.large
        ).background(Color.Yellow).padding(SpaceSmall),
        contentAlignment = Alignment.Center
    ) {
        Text(message)
    }
}
