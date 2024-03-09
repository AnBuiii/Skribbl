package com.anbui.skribbl.platform

import io.ktor.client.engine.HttpClientEngineFactory

expect val engine: HttpClientEngineFactory<*>
