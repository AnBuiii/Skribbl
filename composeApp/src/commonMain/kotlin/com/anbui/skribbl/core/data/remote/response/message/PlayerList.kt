package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent list of player sent by websocket
 */
@Serializable
@SerialName(BaseModel.PLAYER_LIST)
data class PlayerList(
    val players: List<PlayerData>
)
