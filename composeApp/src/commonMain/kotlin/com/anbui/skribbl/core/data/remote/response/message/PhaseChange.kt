package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent phase change message sent by SERVER
 * @param phase current
 * @param timeStamp delay time to next phase in milliseconds
 * @param drawingPlayer player who draw
 */
@Serializable
@SerialName(BaseModel.PHASE_CHANGE)
data class PhaseChange(
    var phase: Phase?,
    var timeStamp: Long,
    val drawingPlayer: String? = null
) : BaseModel() {
    enum class Phase {
        WAITING_FOR_PLAYER,
        WAITING_FOR_START,
        NEW_ROUND,
        GAME_RUNNING,
        SHOW_WORD
    }
}
