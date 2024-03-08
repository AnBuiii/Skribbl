package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent chosen WORD send by DRAW PLAYER
 */
@Serializable
@SerialName(BaseModel.CHOSEN_WORD)
data class ChosenWord(
    val chosenWord: String,
    val roomName: String,
) : BaseModel()
