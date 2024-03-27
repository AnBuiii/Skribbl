package com.anbui.skribbl.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme(
    primary = SkribblColor.Black,
    inversePrimary = SkribblColor.Bright_Orange,
    onPrimary = SkribblColor.White,
    secondary = SkribblColor.Green,
    onSecondary = SkribblColor.Black
)

@Composable
fun SkribblTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = SkribblTypography(),
        content = content,
        shapes = Shapes
    )
}
