package com.anbui.skribbl.feature.game

import androidx.compose.ui.geometry.Offset

sealed class DrawEvent {
    data class BeginDraw(val offset: Offset) : DrawEvent()
    data class OnDraw(val offset: Offset) : DrawEvent()
    data object EndDraw : DrawEvent()
    data object Undo : DrawEvent()
    data class Chat(val message: String) : DrawEvent()
    data object SendChat : DrawEvent()
    data class ChooseWord(val word: String) : DrawEvent()
}
