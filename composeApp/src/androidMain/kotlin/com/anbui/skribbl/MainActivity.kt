package com.anbui.skribbl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.anbui.skribbl.domain.repository.SocketService
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    private lateinit var socket: SocketService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        socket = get()
        Napier.base(DebugAntilog())
        setContent {
            FocusSkribblApp()
        }
    }

    override fun onPause() {
        super.onPause()
        if (!::socket.isInitialized) return
        runBlocking {
            Napier.d { "pause" }
            socket.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!::socket.isInitialized) return
        runBlocking {
            Napier.d { "resume" }
            socket.reconnect()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Napier.d { "destroy" }
    }
}
