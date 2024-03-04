package com.anbui.skribbl.domain.model.message

import kotlinx.serialization.Serializable

/**
 * Default create room request
 * @param name Room's name
 * @param maxPlayer max player of this room
 */
@Serializable
data class CreateRoomRequest(
    val name: String,
    val maxPlayer: Int
)
