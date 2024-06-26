package com.anbui.skribbl.core.presentation.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anbui.skribbl.core.presentation.theme.SkribblColor

@Composable
fun SkribblTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        textStyle = MaterialTheme.typography.bodyMedium,
        onValueChange = onValueChange,
        label = hint?.let {
            {
                Text(it, style = MaterialTheme.typography.bodyMedium)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = SkribblColor.Black,
            unfocusedBorderColor = SkribblColor.Black,
        ),
        keyboardOptions = keyboardOptions
    )
}
