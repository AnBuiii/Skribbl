package com.anbui.skribbl.feature.game

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.core.utils.lineTo
import com.anbui.skribbl.core.utils.moveTo
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
            null
        )


    private val _drawnPath = MutableStateFlow<List<Path>>(emptyList())
    val drawn = _drawnPath
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

    /**
     * start drawing at [offset]
     */
    fun onStart(offset: Offset) {
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

    fun onFinishDraw() {
        Napier.d { "stop" }

        val newPath = _drawingPath.value.toPath()

        screenModelScope.launch(dispatcher.io) {
            _drawnPath.update { it + newPath }
            _drawingPath.update { emptyList() }
        }
    }
}