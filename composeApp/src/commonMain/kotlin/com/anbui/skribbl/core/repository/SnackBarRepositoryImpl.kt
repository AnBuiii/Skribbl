package com.anbui.skribbl.core.repository

import com.anbui.skribbl.domain.repository.SnackBarRepository
import kotlinx.coroutines.flow.MutableSharedFlow

class SnackBarRepositoryImpl() : SnackBarRepository {
    override val message = MutableSharedFlow<String>()
    override suspend fun showSnackBar(text: String?) {
        if (text == null) return
        message.emit(text)
    }
}
