package com.anbui.skribbl.feature.selectRoom

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.core.utils.Resource
import com.anbui.skribbl.domain.model.Room
import com.anbui.skribbl.domain.model.RoomResponse
import com.anbui.skribbl.domain.model.mockRooms
import com.anbui.skribbl.domain.repository.SettingRepository
import com.anbui.skribbl.domain.repository.SnackBarRepository
import com.anbui.skribbl.domain.repository.StartGameService
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SelectRoomScreenModel(
    private val startGameService: StartGameService,
    private val snackBarRepository: SnackBarRepository,
    private val settingRepository: SettingRepository
) : ScreenModel {
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.READY)
    val screenState = _screenState.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ScreenState.READY
    )

    private val _roomQuery = MutableStateFlow("")
    val roomQuery = _roomQuery.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ""
    )

    private val _rooms = MutableStateFlow(mockRooms)
    val room = _rooms.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    fun changeRoomQuery(value: String) {
        _roomQuery.update { value }
    }

    fun searchRoom() {
        screenModelScope.launch {
            val resource = startGameService.getRooms(_roomQuery.value)

            when (resource) {
                is Resource.Error -> {
                    Napier.d { "Error" }
                    snackBarRepository.showSnackBar(resource.message)
                }

                is Resource.Success -> {
                    _rooms.update { resource.data?.map { it.toRoom() } ?: emptyList() }
                }
            }
        }
    }

    fun joinRoom(roomName: String) {
        if (_screenState.value is ScreenState.LOADING) return

        _screenState.update { ScreenState.LOADING }
        screenModelScope.launch {
            val playerName = settingRepository.getName()
            val resource = startGameService.joinRoom(playerName, roomName)

            when (resource) {
                is Resource.Error -> {
                    snackBarRepository.showSnackBar(resource.message)
                    _screenState.update { ScreenState.READY }
                }

                is Resource.Success -> {
                    snackBarRepository.showSnackBar("Join")
                    _screenState.update { ScreenState.DONE }
                }
            }
        }
    }
}

sealed class ScreenState {
    data object READY : ScreenState()
    data object LOADING : ScreenState()
    data object DONE : ScreenState()
}

fun RoomResponse.toRoom(): Room {
    return Room(roomName = this.name, playerCount = playerCount, roomSize = maxPlayer)
}
