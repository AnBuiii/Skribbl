package com.anbui.skribbl.core.data.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow

class SettingManager(
    settings: Settings
) {
    var observableSettings: ObservableSettings = settings as ObservableSettings

    fun setString(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }

    @OptIn(ExperimentalSettingsApi::class)
    fun getString(key: String): Flow<String?> {
        return observableSettings.getStringOrNullFlow(key)
    }

    companion object {
        const val CLIENT_ID = "client_id"
        const val PLAYER_NAME = "playerName"
    }

}
