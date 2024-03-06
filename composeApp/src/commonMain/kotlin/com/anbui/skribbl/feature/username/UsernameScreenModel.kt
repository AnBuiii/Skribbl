package com.anbui.skribbl.feature.username

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class UsernameScreenModel : ScreenModel {

    private val _username = MutableStateFlow("")
    val username = _username.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ""
    )

    fun changeUsername(value: String) {
        _username.update { value }
    }
}