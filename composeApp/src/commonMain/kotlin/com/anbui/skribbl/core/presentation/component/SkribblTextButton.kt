package com.anbui.skribbl.core.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anbui.skribbl.core.presentation.theme.Color
import com.anbui.skribbl.core.presentation.theme.SpaceSmall
import com.anbui.skribbl.core.presentation.theme.debounceClickable

@Composable
fun SkribblTextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit

) {
    Box(
        modifier = Modifier
            .debounceClickable {
                onClick()
            }
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = MaterialTheme.shapes.small
            )
            .padding(SpaceSmall)
            .then(modifier)

    ) {
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}
