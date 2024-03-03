package com.anbui.skribbl.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val id: Int,
    val employee_name: String,
    val employee_salary: Int,
    val employee_age: Int,
    val profile_image: String
)