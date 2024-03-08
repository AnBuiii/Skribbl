package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Present message of list of words drawing player will choose from
 * Send by SERVER
 */
@Serializable
@SerialName(BaseModel.NEW_WORD)
data class NewWords(
    val newWords: List<String>
) : BaseModel()
