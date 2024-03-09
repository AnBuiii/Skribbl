package com.anbui.skribbl.core.repository

import com.anbui.skribbl.domain.repository.SnackBarRepository
import kotlinx.coroutines.flow.MutableSharedFlow

class SnackBarRepositoryImpl(

) : SnackBarRepository {
    override val message = MutableSharedFlow<String>()
    override fun showSnackBar(text: String?) {
        if (text == null) return
        message.tryEmit(text)
    }
}
