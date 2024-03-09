package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This class is NOT IN USE
 */
@Serializable
@SerialName(BaseModel.NOT_BASE_MODEL)
data class NotBaseModel(
    val notFrom: String
) : BaseModel()
