package com.anbui.skribbl.domain.repository

import com.anbui.skribbl.domain.model.Employee

interface TestRepository {
    suspend fun getEmployee(): List<Employee>
}