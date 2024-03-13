package com.anbui

import com.anbui.skribbl.domain.repository.SocketService
import io.github.aakira.napier.Napier
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class SocketHelper : KoinComponent {
    private val socket: SocketService by inject()

    fun reconnect() {
        runBlocking {
            Napier.d { "resume" }
            socket.reconnect()
        }
    }

    fun pause() {
        runBlocking {
            Napier.d { "pause" }
            socket.pause()
        }
    }
}