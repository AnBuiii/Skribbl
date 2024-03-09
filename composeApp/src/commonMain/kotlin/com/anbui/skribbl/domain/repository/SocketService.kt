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
     * Current state of service
     */
    val state: Flow<STATE>

    /**
     * try connect to server
     */
    suspend fun connect()

    suspend fun <T> send(data: T)

    suspend fun disconnect(reason: CloseReason?)

    enum class STATE {
        READY, ONGOING, ERROR
    }
}
