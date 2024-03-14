package com.anbui.skribbl.platform

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import java.util.UUID

class AndroidPlatform : Platform {
    override fun getRandomUUID(): String {
        return UUID.randomUUID().toString()
    }

    @Composable
    override fun getScreenHeight(): Int {
        return LocalConfiguration.current.screenHeightDp
    }

    override fun getEngine(): HttpClientEngineFactory<*> {
        return CIO
    }

    override fun getPlatformName(): String {
        return "Android ${Build.VERSION.SDK_INT}"
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()
