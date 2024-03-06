package com.anbui.skribbl.feature.selectRoom

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.domain.model.Room
import com.anbui.skribbl.domain.model.mockRooms
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SelectRoomScreenModel() : ScreenModel {
    private val _roomQuery = MutableStateFlow("")
    val roomQuery = _roomQuery.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ""
    )

    val _rooms = MutableStateFlow<List<Room>>(mockRooms)
    val room = _rooms.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )


    fun changeRoomQuery(value: String) {
        _roomQuery.update { value }
    }


}
