package com.anbui.skribbl.core.presentation.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.util.fastAny
import androidx.compose.ui.util.fastForEach
import com.anbui.skribbl.platform.Platform
import kotlinx.datetime.Clock


private const val DEBOUNCE_TIME_MILLIS = 1000L

internal interface EventProcessor {
    fun processEvent(event: () -> Unit)

    companion object {
        private val buttonClickMap = mutableMapOf<String, EventProcessor>()
        fun get(id: String): EventProcessor {
            return buttonClickMap.getOrPut(
                id
            ) {
                EventProcessorImpl()
            }
        }
    }
}


private class EventProcessorImpl : EventProcessor {
    private val now: Long
        get() = Clock.System.now().toEpochMilliseconds()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= DEBOUNCE_TIME_MILLIS) {
            event()
        }
        lastEventTimeMs = now
    }
}

@Composable
fun debouncedClick(
    id: String = Platform.INSTANCE.getRandomUUID(),
    onClick: () -> Unit,
): () -> Unit {
    val multipleEventsCutter = remember { EventProcessor.get(id) }
    val newOnClick: () -> Unit = {
        multipleEventsCutter.processEvent { onClick() }
    }
    return newOnClick
}

fun Modifier.debounceClickable(
    onClick: () -> Unit
) = composed {
    val click = debouncedClick { onClick() }
    clickable {
        click()
    }
}

interface Debounce {
    fun isSloped(): Boolean

    companion object {
        private val buttonClickMap = mutableMapOf<String, Debounce>()
        fun get(id: String): Debounce {
            return buttonClickMap.getOrPut(id) {
                DebounceImpl()
            }
        }
    }
}

class DebounceImpl : Debounce {
    private val now
        get() = Clock.System.now().toEpochMilliseconds()

    private var lastClick: Long = 0
    override fun isSloped(): Boolean {
        if (now - lastClick >= DEBOUNCE_TIME_MILLIS) {
            return false
        }
        lastClick = now
        return true
    }
}


fun Modifier.debounceClick(
    id: String = Platform.INSTANCE.getRandomUUID()
) = composed {
    Modifier.pointerInput(Unit) {
        detectTapGestures {

        }
        awaitEachGesture {
            do {
                val event = awaitPointerEvent()
                event.changes.fastForEach { it.consume() }
            } while (event.changes.fastAny { it.pressed })

        }

//        awaitPointerEventScope {
//            Napier.d { "here?" }
//            val down = awaitFirstDown()
//            down.down.consume()
//
//
//            val event = Debounce.get(id)
//
//            if (event.isSloped()) {
//                Napier.d { "Is sloped" }
//                down.consume()
//            } else {
//                Napier.d { "Is ok" }
//            }
//        }

//        detectTapGestures { }
    }
}
