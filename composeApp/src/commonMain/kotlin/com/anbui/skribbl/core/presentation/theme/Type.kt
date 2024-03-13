package com.anbui.skribbl.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SkribblTypography(): Typography {
    return Typography(
        bodyMedium = TextStyle(
            fontFamily = Fonts.handWriting(),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 20.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = Fonts.handWriting(),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 20.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = Fonts.handWriting(),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp
        )
    )
}
