package com.anbui.skribbl.domain.repository

import com.anbui.skribbl.core.utils.Resource
import com.anbui.skribbl.domain.model.RoomResponse

interface StartGameService {
    suspend fun createRoom(name: String, maxPlayer: Int): Resource<Boolean>

    suspend fun joinRoom(username: String, roomName: String): Resource<Unit>

    suspend fun getRooms(roomQuery: String): Resource<List<RoomResponse>>
}
