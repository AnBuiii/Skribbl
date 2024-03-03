package com.anbui.skribbl.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform