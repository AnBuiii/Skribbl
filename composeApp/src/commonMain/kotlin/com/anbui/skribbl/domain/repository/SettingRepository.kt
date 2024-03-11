package com.anbui.skribbl.domain.repository

interface SettingRepository {
    suspend fun getName(): String

    suspend fun getClientId(): String

    fun setName(value: String)

    fun setRoom(name: String)

    suspend fun getRoomName(): String
}
