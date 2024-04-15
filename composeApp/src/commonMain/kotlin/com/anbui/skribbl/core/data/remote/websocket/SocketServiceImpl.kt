package com.anbui.skribbl.core.data.remote.websocket

import com.anbui.skribbl.core.data.remote.response.message.BaseModel
import com.anbui.skribbl.core.data.remote.response.message.Ping
import com.anbui.skribbl.core.utils.BaseSerializerModule
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.domain.repository.SettingRepository
import com.anbui.skribbl.domain.repository.SocketService
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.utils.io.errors.IOException
import io.ktor.websocket.CloseReason
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SocketServiceImpl(
    private val client: HttpClient,
    private val dispatcherProvider: DispatcherProvider,
    private val settingRepository: SettingRepository
) : SocketService {

    override val data: Flow<BaseModel>
        get() = _data

    override val state: Flow<SocketService.STATE>
        get() = _state

    private val _data = MutableSharedFlow<BaseModel>()

    private val _state = MutableStateFlow(SocketService.STATE.READY)

    private var session: DefaultWebSocketSession? = null

    override suspend fun connect() {
        try {
            client.webSocket(
                "/ws/draw",
            ) {
                session = this
                _state.emit(SocketService.STATE.ONGOING)
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val message = frame.readText()
                        val payload =
                            BaseSerializerModule.baseJson.decodeFromString<BaseModel>(message)

                        when (payload) {
                            is Ping -> {
                                val pong: BaseModel = Ping()
                                Napier.d { "send ping" }
                                send(pong)
                            }

                            else -> {
                                _data.emit(payload)
                            }
                        }
                    }
                }
            }
        } catch (e: IOException) {
            Napier.d { "service INTERNET ERROR $e" }
            _state.emit(SocketService.STATE.ERROR)
        } catch (e: Exception) {
            Napier.d { "service unknown error $e" }
            _state.emit(SocketService.STATE.ERROR)
        } finally {
            Napier.d { "service ready" }
            _state.emit(SocketService.STATE.READY)
        }
    }

    override suspend fun <T> send(data: T) {
        val frame = when (data) {
            is BaseModel -> {
                Napier.d { "BaseModel $data" }
                val hm = data as BaseModel
                val json = BaseSerializerModule.baseJson
                json.encodeToString(hm)
            }

            else -> {
                Napier.d { "NOTBaseModel $data" }
                Json.encodeToString(data as Any)
            }
        }
        if (session != null) {
            session?.send(Frame.Text(frame))
        } else {
            Napier.d { "session is null" }
        }
    }

    override suspend fun pause() {
        if (session == null) return
        _state.emit(SocketService.STATE.PAUSE)
    }

    override suspend fun reconnect() {
        if (session == null) return
        if (_state.value != SocketService.STATE.PAUSE) return
        _state.emit(SocketService.STATE.RESUME)
    }

    override suspend fun disconnect(reason: CloseReason?) {
        if (session == null) return
        val closeReason = reason ?: CloseReason(CloseReason.Codes.VIOLATED_POLICY, "unknown error")
        session?.close(closeReason)
        session = null
        _state.emit(SocketService.STATE.READY)
    }
}
