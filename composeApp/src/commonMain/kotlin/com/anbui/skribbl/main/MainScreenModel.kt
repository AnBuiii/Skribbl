package com.anbui.skribbl.main

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.domain.repository.SnackBarRepository
import kotlinx.coroutines.launch

class MainScreenModel(
    private val snackBarRepository: SnackBarRepository
) : ScreenModel {
    val hostState = SnackbarHostState()

    init {
        screenModelScope.launch {
            snackBarRepository.message.collect { message ->
                hostState.showSnackbar(message, duration = SnackbarDuration.Short)
            }
        }
    }
}
