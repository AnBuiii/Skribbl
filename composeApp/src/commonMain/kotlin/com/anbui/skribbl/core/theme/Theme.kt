package com.anbui.skribbl.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    inversePrimary = Color.Bright_Orange,
    onPrimary =  Color.White,
    secondary = Color.Green,
    onSecondary = Color.Black
)

@Composable
fun SkribblTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = SkribblTypography(),
        content = content,
        shapes = Shapes
    )
}