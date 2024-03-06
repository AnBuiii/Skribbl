package com.anbui.skribbl.feature.createRoom

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CreateRoomScreenModel : ScreenModel {
    private val _roomName = MutableStateFlow("")
    val roomName = _roomName.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ""
    )

    private val _roomSize = MutableStateFlow("")
    val roomSize = _roomSize.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ""
    )

    fun changeRoomName(value: String) {
        _roomName.update { value }
    }

    fun changeRoomSize(value: String) {
        _roomSize.update { value }
    }
}