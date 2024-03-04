package com.anbui.skribbl.platform

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual val engine: HttpClientEngineFactory<*>
    get() = Darwin