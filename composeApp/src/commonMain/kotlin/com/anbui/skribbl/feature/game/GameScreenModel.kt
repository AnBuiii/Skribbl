package com.anbui.skribbl.feature.game

import androidx.compose.ui.graphics.Path
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.core.data.remote.response.message.ChosenWord
import com.anbui.skribbl.core.data.remote.response.message.DrawData
import com.anbui.skribbl.core.data.remote.response.message.GameError
import com.anbui.skribbl.core.data.remote.response.message.JoinRoomHandshake
import com.anbui.skribbl.core.data.remote.response.message.NewWords
import com.anbui.skribbl.core.data.remote.response.message.PhaseChange
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

data class SkribblPath(
    val path: Path,
    val thickness: Float = 1f,
    val color: Int = 0

)

class GameScreenModel(
    private val dispatcher: DispatcherProvider,
    private val socketService: SocketService,
    private val snackBarRepository: SnackBarRepository,
    private val settingRepository: SettingRepository,
) : ScreenModel {
    private val _drawingPath = MutableStateFlow<List<DrawData>>(emptyList())
    val drawingPath = _drawingPath
        .map { it.toPath() }
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            SkribblPath(Path(), 0f, 0)
        )

    private val _drawnPath = MutableStateFlow<List<SkribblPath>>(emptyList())
    val drawnPath = _drawnPath
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            emptyList()
        )

    private val _color = MutableStateFlow(0)
    val color = _color.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        0
    )

    private val _thickness = MutableStateFlow(1f)
    val thickness = _thickness.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        1f
    )

    private val _chat = MutableStateFlow("")
    val chat = _chat
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5_000),
            ""
        )

    private val _newWords = MutableStateFlow<List<String>>(emptyList())
    val newWords = _newWords.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    private val _showChooseWordOverlay = MutableStateFlow(false)
    val showChooseWordOverlay = _showChooseWordOverlay.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        false
    )

    private val phase = MutableStateFlow(PhaseChange.Phase.WAITING_FOR_PLAYER)

    private val _timer = MutableStateFlow(0L)

    init {
        observeSocketState()
        observeSocketData()
    }


    fun onEvent(event: DrawEvent) {
        when (event) {
            is DrawEvent.BeginDraw -> {
                screenModelScope.launch(dispatcher.io) {
                    val drawData = DrawData(
                        "",
                        color = _color.value,
                        thickness = _thickness.value,
                        x = event.offset.x,
                        y = event.offset.y,
                        motionEvent = DrawData.MOTION_DRAWING
                    )
                    socketService.send(drawData)

                    _drawingPath.update {
                        it + drawData
                    }
                }
            }

            is DrawEvent.EndDraw -> {
                screenModelScope.launch(dispatcher.io) {
                    val newPath = _drawingPath.value.toPath()
                    val last = _drawingPath.value.last()
                    _drawnPath.update { it + newPath }

                    val drawData = DrawData(
                        "",
                        color = _color.value,
                        thickness = _thickness.value,
                        x = last.x,
                        y = last.y,
                        motionEvent = DrawData.MOTION_UP
                    )
                    socketService.send(drawData)
                    _drawingPath.update { emptyList() }
                }

            }

            is DrawEvent.OnDraw -> {
                screenModelScope.launch {
                    val drawData = DrawData(
                        "",
                        color = _color.value,
                        thickness = _thickness.value,
                        x = event.offset.x,
                        y = event.offset.y,
                        motionEvent = DrawData.MOTION_DRAWING
                    )
                    socketService.send(drawData)
                    _drawingPath.update {
                        it + drawData
                    }
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

            is DrawEvent.ChooseWord -> {
                screenModelScope.launch {
                    val roomName = settingRepository.getRoomName()
                    val chosenWord = ChosenWord(event.word, roomName)
                    socketService.send(chosenWord)
                }
            }
        }
    }

    private fun connectServer() {
        screenModelScope.launch(dispatcher.io) {
            socketService.connect()
        }
    }

    fun phaseHandle(phase: PhaseChange.Phase) {
        when (phase) {
            PhaseChange.Phase.GAME_RUNNING -> {
                _showChooseWordOverlay.update { false }
            }

            else -> {

            }
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

                    is NewWords -> {
                        _newWords.update { data.newWords }
                        _showChooseWordOverlay.update { true }
                    }

                    is DrawData -> {

                    }

                    is PhaseChange -> {
                        data.phase?.let { newPhase ->
                            phase.update { newPhase }
                            phaseHandle(newPhase)
                        }
                        _timer.update { data.timeStamp }
                        Napier.d { "Phase: ${phase.value}, time: ${_timer.value}" }

                    }

                    else -> {
                        Napier.d { data.toString() }
                    }
                }
            }
        }
    }

    override fun onDispose() {
        super.onDispose()
        Napier.d { "dispose" }

    }

}
