package com.anbui.skribbl.domain.model.message

import kotlinx.serialization.Serializable

@Serializable
data class BasicApiResponse(
    val isSuccessful: Boolean,
    val message: String? = null,
)