package com.anbui.skribbl.core.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.anbui.skribbl.core.presentation.theme.SkribblColor
import com.anbui.skribbl.core.presentation.theme.SpaceTiny
import com.anbui.skribbl.core.presentation.theme.debounceClickable
import io.github.anbuiii.compostory.annotation.Compostory

@Compostory
@Composable
fun SkribblTextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    style: TextStyle = MaterialTheme.typography.titleMedium

) {
    Box(
        modifier = Modifier
            .debounceClickable {
                onClick()
            }
            .border(
                width = 2.dp,
                color = SkribblColor.Black,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = SpaceTiny)
            .then(modifier),
        contentAlignment = Alignment.Center

    ) {
        Text(text, style = style)
    }
}
