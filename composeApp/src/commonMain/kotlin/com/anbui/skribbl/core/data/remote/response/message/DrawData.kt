package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent draw data, sent from websocket
 *
 * @param roomName name of current room
 * @param color color of current pen
 * @param thickness thickness of current pen
 * @param motionEvent , sample: up, down
 *
 *
 * [x], [y] draw location
 *
 */
@Serializable
@SerialName(BaseModel.DRAW_DATA)
data class DrawData(
    val roomName: String,
    val color: Int,
    val thickness: Float,
    val x: Float,
    val y: Float,
    val motionEvent: Int
) : BaseModel() {
    companion object {
        const val MOTION_UP = 1
        const val MOTION_DRAWING = 2
    }
}
