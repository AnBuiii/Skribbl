package com.anbui.skribbl

import androidx.compose.ui.window.ComposeUIViewController
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun MainViewController() = ComposeUIViewController { FocusSkribblApp() }

fun debugBuild() {
    Napier.base(DebugAntilog())
}