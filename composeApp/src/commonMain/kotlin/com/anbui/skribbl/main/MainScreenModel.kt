package com.anbui.skribbl.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.anbui.skribbl.domain.repository.SnackBarRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainScreenModel(
    val snackBarRepository: SnackBarRepository
) : ScreenModel {
//    val snackBarMessage = snackBarRepository.message.sha(
//        screenModelScope,
//        SharingStarted.WhileSubscribed(5_000),
//        null
//    )
}