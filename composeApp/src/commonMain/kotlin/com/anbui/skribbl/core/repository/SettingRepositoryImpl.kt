package com.anbui.skribbl.core.repository

import com.anbui.skribbl.core.data.local.SettingManager
import com.anbui.skribbl.domain.repository.SettingRepository
import com.anbui.skribbl.platform.randomUUID
import kotlinx.coroutines.flow.firstOrNull

class SettingRepositoryImpl(
    private val settingManager: SettingManager
) : SettingRepository {
    override suspend fun getName(): String {
        return settingManager.getString(SettingManager.PLAYER_NAME).firstOrNull() ?: ""
    }

    /**
     * get client id
     * warning: no thread safe
     */
    override suspend fun getClientId(): String {
        val clientId = settingManager.getString(SettingManager.CLIENT_ID).firstOrNull()
        if (clientId != null) return clientId

        val newClientId = randomUUID()
        settingManager.setString(SettingManager.CLIENT_ID, newClientId)

        return newClientId
    }


    override fun setName(value: String) {
        settingManager.setString(SettingManager.PLAYER_NAME, value)
    }

    override fun setRoom(name: String) {
        settingManager.setString(SettingManager.ROOM_NAME, name)
    }

    override suspend fun getRoomName(): String {
        return settingManager.getString(SettingManager.ROOM_NAME).firstOrNull() ?: ""
    }
}
