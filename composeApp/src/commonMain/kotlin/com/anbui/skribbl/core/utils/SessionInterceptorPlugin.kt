package com.anbui.skribbl.core.utils

import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.Send
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.parameter

val SessionInterceptorPlugin: (clientId: String) -> ClientPlugin<Unit> = { id ->
    createClientPlugin("SomePlugin") {
        on(Send) { request ->
            request.parameter("client", id)
            proceed(request)
        }
    }
}