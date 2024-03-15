package com.anbui.skribbl.core.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

fun Modifier.drawChat(color: Color = Color.White) =
    composed {
        drawBehind {
            val path = Path()

            val height = size.height
            val width = size.width
            val corner = 10f

            path.apply {
                lineTo(width - corner, 0f)
                arcTo(
                    Rect(
                        topLeft = Offset(width - 2 * corner, 0f),
                        bottomRight = Offset(width, 0f + 2 * corner)
                    ),
                    forceMoveTo = false,
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = 90f
                )
                lineTo(width, height - corner)
                arcTo(
                    Rect(
                        topLeft = Offset(width - 2 * corner, height - 2 * corner),
                        bottomRight = Offset(width, height)
                    ),
                    forceMoveTo = false,
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f
                )
                lineTo(0f + corner + corner, height)
                arcTo(
                    Rect(
                        topLeft = Offset(0f + corner, height - 2 * corner),
                        bottomRight = Offset(0f + 3 * corner, height)
                    ),
                    forceMoveTo = false,
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f
                )
                lineTo(0f + corner, corner)
                lineTo(0f, 0f)
            }
            drawPath(path, color) // background

            drawPath(path, SkribblColor.Black, style = Stroke(2f)) // stroke
        }
    }