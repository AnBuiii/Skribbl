package com.anbui.skribbl.feature.username

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.domain.repository.SettingRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsernameScreenModel(
    private val settingRepository: SettingRepository
) : ScreenModel {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _screenEvent = MutableSharedFlow<UsernameScreenEvent>()
    val screenEvent = _screenEvent.asSharedFlow()

    init {
        screenModelScope.launch {
            _username.update { settingRepository.getName() }
        }
    }

    fun changeUsername(value: String) {
        _username.update { value }
    }

    fun next() {
        screenModelScope.launch {
            settingRepository.setName(_username.value)
            _screenEvent.emit(UsernameScreenEvent.GoNext)
        }
    }
}

sealed class UsernameScreenEvent {
    data object GoNext : UsernameScreenEvent()
}
