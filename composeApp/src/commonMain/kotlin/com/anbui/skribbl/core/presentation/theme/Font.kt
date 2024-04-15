package com.anbui.skribbl.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.handwriting

object Fonts {
    @Composable
    @OptIn(ExperimentalResourceApi::class)
    fun handWriting() = FontFamily(
        Font(Res.font.handwriting)
    )
}
