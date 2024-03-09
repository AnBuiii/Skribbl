package com.anbui.skribbl.feature.game

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.core.utils.toPath
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class GameScreenModel(
    private val dispatcher: DispatcherProvider
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

    /**
     * start drawing at [offset]
     */

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
        }

        fun onChangeChat(value: String) {
            _chat.update { value }
        }

        fun sendChat() {
            //
        }
    }

    fun onChangeChat(value: String) {
        _chat.update { value }
    }

    fun sendChat() {
    }
}
