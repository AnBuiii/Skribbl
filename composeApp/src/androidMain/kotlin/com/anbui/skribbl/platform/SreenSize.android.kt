package com.anbui.skribbl.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun getScreenWidth(): Int {
    return LocalConfiguration.current.screenHeightDp
}
