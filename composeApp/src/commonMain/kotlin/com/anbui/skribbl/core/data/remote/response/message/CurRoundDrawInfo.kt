package com.anbui.skribbl.core.data.remote.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(BaseModel.CUR_ROUND_DRAW_INFO)
data class CurRoundDrawInfo(
    val data: List<String>
) : BaseModel()
