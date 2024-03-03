package com.anbui.skribbl.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeRespond(
    val status: String,
    val data: List<Employee>,
    val message: String
)
