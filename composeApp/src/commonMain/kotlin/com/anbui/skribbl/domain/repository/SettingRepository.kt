package com.anbui.skribbl.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    fun getName(): Flow<String?>
    fun setName(value: String)
}