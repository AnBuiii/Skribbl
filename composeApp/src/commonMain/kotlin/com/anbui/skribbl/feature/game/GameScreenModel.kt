package com.anbui.skribbl.feature.game

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.core.data.remote.response.message.GameError
import com.anbui.skribbl.core.data.remote.response.message.JoinRoomHandshake
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.core.utils.toPath
import com.anbui.skribbl.domain.repository.SettingRepository
import com.anbui.skribbl.domain.repository.SnackBarRepository
import com.anbui.skribbl.domain.repository.SocketService
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.error_unknown

class GameScreenModel(
    private val dispatcher: DispatcherProvider,
    private val socketService: SocketService,
    private val snackBarRepository: SnackBarRepository,
    private val settingRepository: SettingRepository,
) : ScreenModel {

    private val _drawingPath = MutableStateFlow<List<Offset>>(emptyList())
    val drawingPath = _drawingPath
        .map { it.toPath() }
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            Path()
        )

    private val _drawnPath = MutableStateFlow<List<Path>>(emptyList())
    val drawnPath = _drawnPath
        .map { paths ->
            Path().apply {
                paths.forEach { p ->
                    addPath(p)
                }
            }
        }
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            Path()
        )

    private val _chat = MutableStateFlow("")
    val chat = _chat
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5_000),
            ""
        )

    init {
        observeSocketState()
        observeSocketData()
    }


    fun onEvent(event: DrawEvent) {
        when (event) {
            is DrawEvent.BeginDraw -> {
                Napier.d { event.offset.toString() }
                _drawingPath.update {
                    it + event.offset
                }
            }

            is DrawEvent.EndDraw -> {
                Napier.d { "stop" }

                val newPath = _drawingPath.value.toPath()

                _drawnPath.update { it + newPath }
                _drawingPath.update { emptyList() }
            }

            is DrawEvent.OnDraw -> {
                Napier.d { event.offset.toString() }
                _drawingPath.update {
                    it + event.offset
                }
            }

            DrawEvent.Undo -> {
                _drawnPath.update {
                    it.dropLast(1)
                }
            }

            is DrawEvent.Chat -> {
                _chat.update { event.message }
            }

            DrawEvent.SendChat -> {
                // TODO socket.chat
                _chat.update { "" }
            }
        }
    }

    private fun connectServer() {
        screenModelScope.launch(dispatcher.io) {
            socketService.connect()
        }
    }

    private fun observeSocketState() {
        screenModelScope.launch(dispatcher.io) {
            socketService.state.collect { socketState ->
                when (socketState) {
                    SocketService.STATE.READY -> {
                        connectServer()
                    }

                    SocketService.STATE.ERROR -> {
                        snackBarRepository.showSnackBar(getString(Res.string.error_unknown))
                    }

                    SocketService.STATE.ONGOING -> {
                        val joinRoomHandshake = JoinRoomHandshake(
                            settingRepository.getName(),
                            settingRepository.getClientId(),
                            settingRepository.getRoomName()
                        )
                        socketService.send(joinRoomHandshake)
                    }
                }
            }

        }
    }

    private fun observeSocketData() {
        screenModelScope.launch(dispatcher.io) {
            socketService.data.collect { data ->
                when (data) {
                    is GameError -> {
                        snackBarRepository.showSnackBar(getString(GameError.gameErrorMapper(data.errorType)))
                    }

                    else -> {
                        Napier.d { data.toString() }
                    }
                }
            }
        }
    }

}
