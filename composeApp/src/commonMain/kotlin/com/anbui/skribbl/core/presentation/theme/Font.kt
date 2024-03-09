package com.anbui.skribbl.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.handwriting
import skribbl.composeapp.generated.resources.sofia_pro_medium

object Fonts {
    @Composable
    @OptIn(ExperimentalResourceApi::class)
    fun handWriting() = FontFamily(
        Font(Res.font.handwriting)
    )

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun sofia_pro() = FontFamily(
        Font(Res.font.sofia_pro_medium, FontWeight.Medium),

    )
}
