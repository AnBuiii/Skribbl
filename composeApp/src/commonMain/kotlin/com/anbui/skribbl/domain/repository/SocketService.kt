package com.anbui.skribbl.domain.repository

import com.anbui.skribbl.core.data.remote.response.message.BaseModel
import io.ktor.websocket.CloseReason
import kotlinx.coroutines.flow.Flow

interface SocketService {
    /**
     * Data flow from websocket
     */
    val data: Flow<BaseModel>

    /**
     * try connect to server
     */
    fun connect()

    suspend fun <T> send(data: T)

    suspend fun disconnect(reason: CloseReason?)
}