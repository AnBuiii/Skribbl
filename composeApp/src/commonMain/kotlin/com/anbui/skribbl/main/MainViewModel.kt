package com.anbui.skribbl.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainViewModel : ScreenModel {
    private val _text = MutableStateFlow("")

    val text = _text.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(1000L),
        ""
    )

    fun changeText(value: String) {
        _text.update { value }
    }
}