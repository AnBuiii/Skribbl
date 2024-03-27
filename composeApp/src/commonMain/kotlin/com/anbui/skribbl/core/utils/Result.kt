package com.anbui.skribbl.core.utils

sealed class Resource<out T>(data: T? = null, message: String? = null) {
    data class Success<T>(val data: T? = null) : Resource<T>(data = data)
    data class Error<T>(val data: T? = null, val message: String? = null) :
        Resource<T>(data, message)
}

