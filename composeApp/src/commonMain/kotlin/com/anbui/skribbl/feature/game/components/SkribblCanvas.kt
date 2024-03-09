package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import io.github.aakira.napier.Napier

@Composable
fun SkribblCanvas(
    modifier: Modifier = Modifier,
    drawnPath: Path,
    drawingPath: Path,
    onBeginDraw: (Offset) -> Unit,
    onEndDraw: () -> Unit,
    onDraw: (Offset) -> Unit
) {
    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = onBeginDraw,
                    onDragEnd = onEndDraw
                ) { change, _ ->
                    Napier.d { "asdasd" }
                    onDraw(change.position)
                }
            }
    ) {
        drawPath(
            drawnPath,
            Color.Black,
            style = Stroke(
                width = 5f,
                pathEffect = PathEffect.cornerPathEffect(50f)
            )
        )

        drawPath(
            drawingPath,
            Color.Black,
            style = Stroke(
                width = 3f,
                pathEffect = PathEffect.cornerPathEffect(50f)
            )
        )
    }
}
