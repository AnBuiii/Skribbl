package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent in game message sent by server
 */
@Serializable
@SerialName(BaseModel.ANNOUNCEMENT)
data class Announcement(
    val message: String,
    val timeStamp: Long,
    val announcementType: Int
) : BaseModel() {
    companion object {
        const val PLAYER_GUEST_WORD = 0
        const val PLAYER_JOINED = 1
        const val PLAYER_LEFT = 2
        const val EVERY_BODY_GUESS_IT = 3
    }
}
