package com.anbui.skribbl.core.repository

import com.anbui.skribbl.core.data.local.SettingManager
import com.anbui.skribbl.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow

class SettingRepositoryImpl(
    private val settingManager: SettingManager
) : SettingRepository {
    override fun getName(): Flow<String?> {
        return settingManager.getString("name")
    }

    override fun setName(value: String) {
        settingManager.set("name", value)
    }
}