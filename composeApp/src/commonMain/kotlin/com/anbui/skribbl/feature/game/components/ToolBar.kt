package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anbui.skribbl.core.presentation.theme.SkribblColor
import com.anbui.skribbl.core.presentation.theme.SpaceSmall

/**
 * Top tool bar for undo, choose color,...
 */
@Composable
fun ToolBar(
    modifier: Modifier = Modifier,
    onUndo: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().background(SkribblColor.White).padding(SpaceSmall),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onUndo) {
            Icon(Icons.AutoMirrored.Default.ArrowBack, "")
        }

    }
}