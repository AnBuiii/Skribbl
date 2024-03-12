package com.anbui.skribbl.feature.createRoom

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.core.utils.Resource
import com.anbui.skribbl.domain.repository.SnackBarRepository
import com.anbui.skribbl.domain.repository.StartGameService
import com.anbui.skribbl.feature.selectRoom.ScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateRoomScreenModel(
    private val startGameService: StartGameService,
    private val snackBarRepository: SnackBarRepository
) : ScreenModel {
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.READY)

    private val _screenEvent = MutableSharedFlow<CreateRoomScreenEvent>()
    val screenEvent = _screenEvent.shareIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000)
    )

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

    fun createRoom() {
        screenModelScope.launch {
            val resource =
                startGameService.createRoom(_roomName.value, _roomSize.value.toIntOrNull() ?: 0)
            when (resource) {
                is Resource.Error -> {
                    snackBarRepository.showSnackBar(resource.message)
                    _screenState.update { ScreenState.READY }
                }

                is Resource.Success -> {
                    snackBarRepository.showSnackBar("Create success")
                    _screenEvent.emit(CreateRoomScreenEvent.GoNext)
                }
            }
        }
    }
}

sealed class CreateRoomScreenEvent {
    data object GoNext : CreateRoomScreenEvent()
}