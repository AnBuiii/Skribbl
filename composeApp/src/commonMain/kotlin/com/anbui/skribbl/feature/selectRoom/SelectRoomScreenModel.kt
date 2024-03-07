package com.anbui.skribbl.feature.selectRoom

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.core.utils.Resource
import com.anbui.skribbl.domain.model.Room
import com.anbui.skribbl.domain.model.mockRooms
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
    private val snackBarRepository: SnackBarRepository
) : ScreenModel {
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

    fun searchRoom() {
        screenModelScope.launch {
            val resource = startGameService.getRooms(_roomQuery.value)

            when (resource) {
                is Resource.Error -> {
                    Napier.d { "Error" }
                    snackBarRepository.showSnackBar(resource.message ?: "error")
                }

                is Resource.Success -> {

                }
            }
        }
    }
}
