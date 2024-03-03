package com.anbui.skribbl.core.utils

import com.anbui.skribbl.platform.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}