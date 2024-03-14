package com.anbui.skribbl.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSUUID
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override fun getRandomUUID(): String {
        return NSUUID().UUIDString()
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun getScreenHeight(): Int {
        return with(LocalDensity.current) {
            LocalWindowInfo.current.containerSize.height.toDp().value.toInt()
        }
    }

    override fun getEngine(): HttpClientEngineFactory<*> {
        return Darwin
    }

    override fun getPlatformName(): String {
        return UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    }

}

actual fun getPlatform(): Platform = IOSPlatform()