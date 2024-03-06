package com.anbui.skribbl.core.data.network

import com.anbui.skribbl.core.utils.Constants.CREATE_ROOM_ROUTE
import com.anbui.skribbl.domain.model.message.BasicApiResponse
import com.anbui.skribbl.domain.model.message.CreateRoomRequest
import com.anbui.skribbl.domain.repository.StartGameService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class StartGameImpl(
    private val client: HttpClient
) : StartGameService {
    override suspend fun createRoom(name: String, maxPlayer: Int): Boolean {
        return try {
            val respond = client.post(CREATE_ROOM_ROUTE) {
                contentType(ContentType.Application.Json)
                setBody(CreateRoomRequest(name, maxPlayer))
            }.body<BasicApiResponse>()
            respond.isSuccessful
        } catch (e: Exception){ //TODO
            e.printStackTrace()
            false
        }
    }
    override suspend fun joinRoom(username: String, roomName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getRooms(searchQuery: String): List<*> {
        TODO("Not yet implemented")
    }
}