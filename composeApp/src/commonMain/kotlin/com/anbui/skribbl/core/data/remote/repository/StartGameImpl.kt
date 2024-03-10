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
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException
import org.jetbrains.compose.resources.getString
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.check_internet_connection
import skribbl.composeapp.generated.resources.error_unknown

class StartGameImpl(
    private val client: HttpClient,
) : StartGameService {
    override suspend fun createRoom(name: String, maxPlayer: Int): Resource<Unit> {
        return try {
            val response = client.post(CREATE_ROOM_ROUTE) {
                setBody(CreateRoomRequest(name, maxPlayer))
            }
            val body = response.body<BasicApiResponse>()
            if (response.status != HttpStatusCode.OK) {
                Resource.Error(message = getString(Res.string.error_unknown))
            } else if (!body.isSuccessful) {
                Resource.Error(message = body.message)
            } else {
                Resource.Success()
            }
        } catch (_: IOException) {
            Resource.Error(message = getString(Res.string.check_internet_connection))
        } catch (e: Exception) {
            Resource.Error(message = getString(Res.string.error_unknown))
        }
    }

    /**
     * Handle join room request, response is [BasicApiResponse]
     */
    override suspend fun joinRoom(username: String, roomName: String): Resource<Unit> {
        return try {
            val response = client.get(JOIN_ROOM_ROUTE) {
                parameter("username", username)
                parameter("roomName", roomName)
            }
            val body = response.body<BasicApiResponse>()

            if (response.status != HttpStatusCode.OK) {
                Resource.Error(message = getString(Res.string.error_unknown))
            } else if (!body.isSuccessful) {
                Resource.Error(message = body.message)
            } else {
                Resource.Success()
            }
        } catch (_: IOException) {
            Resource.Error(message = getString(Res.string.check_internet_connection))
        } catch (e: Exception) {
            Resource.Error(message = getString(Res.string.error_unknown))
        }
    }

    override suspend fun getRooms(roomQuery: String): Resource<List<RoomResponse>> {
        return try {
            val response = client.get(GET_ROOM_ROUTE) {
                parameter("roomQuery", roomQuery)
            }
            val body = response.body<List<RoomResponse>>()
            if (response.status != HttpStatusCode.OK) {
                Resource.Error(null, "Username or room name cannot empty")
            } else  {
                Resource.Success(body)
            }
        } catch (_: IOException) {
            Resource.Error(message = getString(Res.string.check_internet_connection))
        } catch (e: Exception) {
            Resource.Error(message = getString(Res.string.error_unknown))
        }
    }
}

