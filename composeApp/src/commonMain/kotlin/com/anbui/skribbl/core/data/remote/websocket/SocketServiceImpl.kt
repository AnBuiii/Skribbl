package com.anbui.skribbl.core.data.remote.websocket

import com.anbui.skribbl.core.data.remote.response.message.BaseModel
import com.anbui.skribbl.core.utils.BaseSerializerModule
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.domain.repository.SettingRepository
import com.anbui.skribbl.domain.repository.SocketService
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.parameter
import io.ktor.util.generateNonce
import io.ktor.utils.io.errors.IOException
import io.ktor.websocket.CloseReason
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SocketServiceImpl(
    private val client: HttpClient,
    private val dispatcherProvider: DispatcherProvider,
    private val settingRepository: SettingRepository
) : SocketService {

    override val data: Flow<BaseModel>
        get() = channel.consumeAsFlow()


    private val channel = Channel<BaseModel>(8)
    private var job: Job? = null
    private var session: DefaultWebSocketSession? = null

    override suspend fun connect() {
        try {
            client.webSocket(
                "/ws/draw",
                request = {
                    parameter("client", generateNonce())
                }
            ) {
                session = this
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val message = frame.readText()
                        val payload =
                            BaseSerializerModule.baseJson.decodeFromString<BaseModel>(message)
                        channel.send(payload)
                    }
                }

            }
        } catch (e: IOException) {
            Napier.d { "INTERNET ERROR" }
            // internet
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override suspend fun <T> send(data: T) {
        val frame = when (data) {
            is BaseModel -> {
                val json = BaseSerializerModule.baseJson
                json.encodeToString(data)
            }

            else -> {
                Json.encodeToString(data as Any)
            }
        }
        session?.send(Frame.Text(frame))
    }

    override suspend fun disconnect(reason: CloseReason?) {
        val closeReason = reason ?: CloseReason(CloseReason.Codes.VIOLATED_POLICY, "unknown error")
        session?.close(closeReason)
        session = null

        job?.cancel()
        job = null
    }


}
