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
 * [fromX], [fromY], [toX], [toY] draw location
 *
 */
@Serializable
@SerialName(BaseModel.DRAW_DATA)
data class DrawData(
    val roomName: String,
    val color: Int,
    val thickness: Float,
    val fromX: Float,
    val fromY: Float,
    val toX: Float,
    val toY: Float,
    val motionEvent: Int
) : BaseModel()
