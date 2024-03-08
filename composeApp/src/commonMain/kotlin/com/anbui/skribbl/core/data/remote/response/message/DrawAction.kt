package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(BaseModel.DRAW_ACTION)
data class DrawAction(
    val action: Int
) : BaseModel() {
    companion object {
        const val ACTION_CLEAR = 0

        const val ACTION_UNDO = 1
    }
}
