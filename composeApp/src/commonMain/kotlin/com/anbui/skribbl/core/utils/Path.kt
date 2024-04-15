package com.anbui.skribbl.core.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import com.anbui.skribbl.core.data.remote.response.message.DrawData
import com.anbui.skribbl.feature.game.SkribblPath

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

fun List<DrawData>.toPath(): SkribblPath {
    val path = Path()
    if (size >= 2) {
        path.moveTo(this[0].x, this[0].y)
        for (i in 1..lastIndex) {
            path.lineTo(this[i].x, this[i].y)
        }
    }
    return SkribblPath(path, firstOrNull()?.thickness ?: 1f, firstOrNull()?.color ?: 0)
}
