package com.anbui.skribbl.domain.repository

interface StartGameService {
    suspend fun createRoom(name: String, maxPlayer: Int): Boolean

    suspend fun joinRoom(username: String, roomName: String)

    suspend fun getRooms(searchQuery: String): List<*>
}