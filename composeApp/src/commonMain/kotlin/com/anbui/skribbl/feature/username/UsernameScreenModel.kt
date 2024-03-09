package com.anbui.skribbl.feature.username

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.domain.repository.SettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsernameScreenModel(
    private val settingRepository: SettingRepository
) : ScreenModel {

    private val _username = MutableStateFlow("")
    val username = _username.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ""
    )

    init {
        screenModelScope.launch {
            _username.update { settingRepository.getName().firstOrNull() ?: "" }
        }
    }

    private val _success = MutableStateFlow(false)
    val success = _success.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        false
    )

    fun changeUsername(value: String) {
        _username.update { value }
    }

    fun next() {
        settingRepository.setName(_username.value)
        _success.update { true }
    }
}
