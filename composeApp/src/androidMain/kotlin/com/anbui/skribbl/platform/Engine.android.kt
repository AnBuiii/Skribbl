package com.anbui.skribbl.platform

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

actual val engine: HttpClientEngineFactory<*>
    get() = CIO
