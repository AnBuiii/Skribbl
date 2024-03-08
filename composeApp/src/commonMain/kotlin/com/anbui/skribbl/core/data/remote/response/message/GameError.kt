package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent in game error message
 */
@Serializable
@SerialName(BaseModel.GAME_ERROR)
data class GameError(
    val errorType: Int,
) : BaseModel() {
    companion object {
        const val ERROR_ROOM_NOT_FOUND = 0
    }
}
