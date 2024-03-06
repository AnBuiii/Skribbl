package com.anbui.skribbl.feature.start

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.domain.repository.SettingRepository
import com.anbui.skribbl.domain.repository.StartGameService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class StartScreenModel(
    private val settingRepository: SettingRepository,
    private val startGameService: StartGameService,
    private val dispatcherProvider: DispatcherProvider
) : ScreenModel {

    val name = settingRepository.getName().stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5000),
        null
    )

    private val _showContent = MutableStateFlow(true)
    val showContent = _showContent.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    fun toggle() {
//        _showContent.update { !it }
        listOf("bui", "le", "hoai", "an").random().let {
            settingRepository.setName(it)
        }
//        screenModelScope.launch(dispatcherProvider.io) {
//            val a = startGameService.createRoom("ok", 3)
//            Napier.d(a.toString())
//        }
    }

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