package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent websocket ping
 */
@Serializable
@SerialName(BaseModel.PING)
data class Ping(
    val nothing: String? = null
) : BaseModel()
