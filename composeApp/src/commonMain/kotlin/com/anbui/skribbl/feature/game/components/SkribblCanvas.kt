package com.anbui.skribbl.feature.game.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import com.anbui.skribbl.feature.game.SkribblPath

@Composable
fun SkribblCanvas(
    modifier: Modifier = Modifier,
    drawnPath: List<SkribblPath>,
    drawingPath: SkribblPath,
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
                    onDraw(change.position)
                }
            }
    ) {

        drawnPath.forEach {
            drawPath(
                it.path,
                Color.Black,
                style = Stroke(
                    width = it.thickness,
                    pathEffect = PathEffect.cornerPathEffect(50f)
                )
            )
        }

        drawPath(
            drawingPath.path,
            Color.Black,
            style = Stroke(
                width = drawingPath.thickness,
                pathEffect = PathEffect.cornerPathEffect(50f)
            )
        )
    }
}
