package com.anbui.skribbl.domain.repository

import kotlinx.coroutines.flow.Flow

interface SnackBarRepository {
    val message: Flow<String>

    fun showSnackBar(text: String?)
}
