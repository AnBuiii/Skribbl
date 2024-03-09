package com.anbui.skribbl.platform

import java.util.UUID

actual fun randomUUID(): String = UUID.randomUUID().toString()