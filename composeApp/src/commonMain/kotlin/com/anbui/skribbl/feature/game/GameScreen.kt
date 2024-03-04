package com.anbui.skribbl.feature.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import org.koin.compose.koinInject

@Composable
fun GameScreen(
    screenModel: GameScreenModel = koinInject()
) {
    val path by screenModel.drawn.collectAsState()
    val drawingPath by screenModel.drawingPath.collectAsState()

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = screenModel::onStart,
                    onDragEnd = screenModel::onFinishDraw
                ) { change, dragAmount ->
                    screenModel.onDraw(change.position)
                }
            }
    ) {
        drawPath(
            path, Color.Black, style = Stroke(
                width = 5f,
                pathEffect = PathEffect.cornerPathEffect(50f)
            )
        )

        drawingPath?.let {
            drawPath(
                it, Color.Black, style = Stroke(
                    width = 3f,
                    pathEffect = PathEffect.cornerPathEffect(50f)
                )
            )
        }
    }
}