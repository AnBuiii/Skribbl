package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Sent wherever [Room.phase] switch to [Room.Phase.GAME_RUNNING].
 * @param drawingPlayer player who draw this game
 * @param word the word other have to guess
 */
@Serializable
@SerialName(BaseModel.GAME_STATE)
data class GameState(
    val drawingPlayer: String,
    val word: String
) : BaseModel()
