package com.anbui.skribbl.platform
import platform.Foundation.NSUUID


actual fun randomUUID(): String = NSUUID().UUIDString()