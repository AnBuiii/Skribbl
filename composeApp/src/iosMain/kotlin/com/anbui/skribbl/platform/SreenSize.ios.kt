package com.anbui.skribbl.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenHeight(): Int {
    return with(LocalDensity.current) {
        LocalWindowInfo.current.containerSize.height.toDp().value.toInt()
    }
}