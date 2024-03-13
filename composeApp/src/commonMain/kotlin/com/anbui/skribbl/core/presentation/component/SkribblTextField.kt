package com.anbui.skribbl.core.presentation.component


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anbui.skribbl.core.presentation.theme.Color

@Composable
fun SkribblTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String? = null
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        textStyle = MaterialTheme.typography.bodyMedium,
        onValueChange = onValueChange,
        placeholder = hint?.let {
            {
                Text(it, style = MaterialTheme.typography.bodyMedium)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black.copy(alpha = 0.8f),
        )
    )
}
