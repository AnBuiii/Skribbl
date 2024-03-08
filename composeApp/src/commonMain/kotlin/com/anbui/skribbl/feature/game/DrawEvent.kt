package com.anbui.skribbl.feature.game

import androidx.compose.ui.geometry.Offset

sealed class DrawEvent {
    data class BeginDraw(val offset: Offset) : DrawEvent()
    data class OnDraw(val offset: Offset) : DrawEvent()
    data object EndDraw : DrawEvent()
}
