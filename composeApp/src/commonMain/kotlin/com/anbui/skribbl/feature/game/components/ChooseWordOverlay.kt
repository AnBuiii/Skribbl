package com.anbui.skribbl.feature.game.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.anbui.skribbl.core.presentation.theme.SpaceMedium
import org.jetbrains.compose.resources.stringResource
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.choose_your_word

@Composable
fun ChooseWordOverlay(
    isVisible: Boolean,
    words: List<String>,
    onWordClick: (String) -> Unit
) {
    AnimatedVisibility(isVisible) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                SpaceMedium,
                Alignment.CenterVertically
            )
        ) {
            Text(stringResource(Res.string.choose_your_word))
            words.forEach { word ->
                WordItem(word) {
                    onWordClick(word)
                }
            }
        }
    }
}
