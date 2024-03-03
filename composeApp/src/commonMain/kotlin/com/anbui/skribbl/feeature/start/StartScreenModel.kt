package com.anbui.skribbl.feeature.start

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.domain.repository.TestRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StartScreenModel(
    private val testRepository: TestRepository
) : ScreenModel {

    private val _showContent = MutableStateFlow(false)
    val showContent = _showContent.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    fun toggle() {
        _showContent.update { !it }
        screenModelScope.launch {
            val a = testRepository.getEmployee()
            Napier.d(a.toString())
        }
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