package com.anbui.skribbl.domain.repository

interface TestRepository {
    suspend fun getEmployee(): List<*>
}
