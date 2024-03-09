package com.anbui.skribbl.core.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class BasicApiResponse(
    val isSuccessful: Boolean,
    val message: String? = null,
)
