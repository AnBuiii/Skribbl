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
import kotlinx.coroutines.launch

class GameScreenModel(
    private val dispatcher: DispatcherProvider
) : ScreenModel {

    private val _drawingPath = MutableStateFlow<List<Offset>>(emptyList())
    val drawingPath = _drawingPath
        .map {
            it.toPath()
        }
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
    fun onBeginDraw(offset: Offset) {
        Napier.d { offset.toString() }
        _drawingPath.update {
            it + offset
        }

    }

    /**
     *
     */
    fun onDraw(offset: Offset) {
        Napier.d { offset.toString() }
        _drawingPath.update {
            it + offset
        }

    }

    fun onEndDraw() {
        Napier.d { "stop" }

        val newPath = _drawingPath.value.toPath()

        screenModelScope.launch(dispatcher.io) {
            _drawnPath.update { it + newPath }
            _drawingPath.update { emptyList() }
        }
    }

    fun onChangeChat(value: String) {
        _chat.update { value }
    }

    fun sendChat(){
        //
    }
}

