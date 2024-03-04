package com.anbui.skribbl.core.data.network

import com.anbui.skribbl.core.utils.Constants.CREATE_ROOM_ROUTE
import com.anbui.skribbl.domain.model.message.BasicApiResponse
import com.anbui.skribbl.domain.model.message.CreateRoomRequest
import com.anbui.skribbl.domain.repository.StartGameService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.setBody

class StartGameImpl(
    private val client: HttpClient
) : StartGameService {
    override suspend fun createRoom(name: String, maxPlayer: Int): Boolean {
        val respond = client.get(CREATE_ROOM_ROUTE) {
            setBody(CreateRoomRequest(name, maxPlayer))
        }.body<BasicApiResponse>()
        return respond.isSuccessful
    }

    override suspend fun joinRoom(username: String, roomName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getRooms(searchQuery: String): List<*> {
        TODO("Not yet implemented")
    }
}