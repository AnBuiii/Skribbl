package com.anbui.skribbl.core.data.remote.repository

import com.anbui.skribbl.core.data.remote.response.BasicApiResponse
import com.anbui.skribbl.core.utils.APIConstant.CREATE_ROOM_ROUTE
import com.anbui.skribbl.core.utils.APIConstant.GET_ROOM_ROUTE
import com.anbui.skribbl.core.utils.APIConstant.JOIN_ROOM_ROUTE
import com.anbui.skribbl.core.utils.Resource
import com.anbui.skribbl.domain.model.RoomResponse
import com.anbui.skribbl.domain.model.message.CreateRoomRequest
import com.anbui.skribbl.domain.repository.StartGameService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class StartGameImpl(
    private val client: HttpClient
) : StartGameService {
    override suspend fun createRoom(name: String, maxPlayer: Int): Resource<Boolean> {
        return try {
            val response = client.post(CREATE_ROOM_ROUTE) {
                contentType(ContentType.Application.Json)
                setBody(CreateRoomRequest(name, maxPlayer))
            }.body<BasicApiResponse>()
            if (response.isSuccessful) {
                Resource.Success(true)
            } else {
                Resource.Error(response.isSuccessful, response.message)
            }
        } catch (e: IOException) {
            Resource.Error(null, "Please check your internet")
        } catch (e: Exception) {
            Resource.Error(null, "Unknown error")
        }
    }

    /**
     * Handle join room request, response is [BasicApiResponse]
     */
    override suspend fun joinRoom(username: String, roomName: String): Resource<Unit> {
        try {
            println("okok")
            val response = client.get(JOIN_ROOM_ROUTE) {
                parameter("username", username)
                parameter("roomName", roomName)
            }
            if (response.status == HttpStatusCode.BadRequest) {
                return Resource.Error(null, "Username or room name cannot empty")
            }
            val basicResponse = response.body<BasicApiResponse>()

            return if (basicResponse.isSuccessful) {
                Resource.Success()
            } else {
                Resource.Error(null, basicResponse.message)
            }
        } catch (e: Exception) {
            return Resource.Error(null, "Unknown error")
        }
    }

    override suspend fun getRooms(roomQuery: String): Resource<List<RoomResponse>> {
        try {
            val response = client.get(GET_ROOM_ROUTE) {
                parameter("roomQuery", roomQuery)
            }
            if (response.status == HttpStatusCode.BadRequest) {
                return Resource.Error(null, "Room query cannot empty")
            }

            val roomResponse = response.body<List<RoomResponse>>()

            return Resource.Success(roomResponse)
        } catch (e: IOException) {
            return Resource.Error(null, "Please check your internet")
        } catch (e: Exception) {
            return Resource.Error(null, "Unknown error")
        }
    }
}
