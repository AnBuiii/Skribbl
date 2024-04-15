package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.error_unknown
import skribbl.composeapp.generated.resources.no_rooms_found

/**
 * Represent in game error message
 */
@Serializable
@SerialName(BaseModel.GAME_ERROR)
data class GameError(
    val errorType: Int,
) : BaseModel() {
    companion object {
        private const val ERROR_ROOM_NOT_FOUND = 0

        fun gameErrorMapper(errorType: Int): StringResource {
            return when (errorType) {
                ERROR_ROOM_NOT_FOUND -> Res.string.no_rooms_found
                else -> Res.string.error_unknown
            }
        }
    }
}
