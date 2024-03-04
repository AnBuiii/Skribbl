package com.anbui.skribbl.core.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

fun Path.moveTo(offset: Offset) {
    this.moveTo(offset.x, offset.y)
}

fun Path.lineTo(offset: Offset) {
    this.lineTo(offset.x, offset.y)
}

fun List<Offset>.toPath(): Path {
    val result = Path()
    if (size >= 2) {
        result.moveTo(this[0])
        for (i in 1..lastIndex) {
            result.lineTo(this[i])
        }
    }
    return result
}