package com.anbui.skribbl.platform

import androidx.compose.runtime.Composable
import io.ktor.client.engine.HttpClientEngineFactory

interface Platform {
    fun getRandomUUID(): String

    @Composable
    fun getScreenHeight(): Int

    fun getEngine(): HttpClientEngineFactory<*>
    fun getPlatformName(): String

    companion object {
        val INSTANCE = getPlatform()
    }
}

expect fun getPlatform(): Platform
