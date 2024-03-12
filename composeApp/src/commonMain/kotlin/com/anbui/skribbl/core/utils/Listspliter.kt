package com.anbui.skribbl.core.utils

fun <T> List<T>.splitBy(included: Boolean = false, selector: (T) -> Boolean): List<List<T>> {
    val result = mutableListOf<List<T>>()
    val temp = mutableListOf<T>()
    forEach { item ->
        if (!selector(item)) {
            temp.add(item)
        } else {
            if (included) {
                temp.add(item)
            }
            result.add(temp.toList())
            temp.clear()
        }
    }
    if (temp.isNotEmpty()) result.add(temp)
    return result
}