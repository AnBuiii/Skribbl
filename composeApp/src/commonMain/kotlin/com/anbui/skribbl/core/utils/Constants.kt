package com.anbui.skribbl.core.utils

object Constants {
    private const val PORT = "8080"
    private const val IP_DEVICE = "10.10.10.109"
    const val IP_EMULATOR = "10.0.2.2"

    private const val ADDRESS = "http://${IP_DEVICE}:${PORT}"

    const val CREATE_ROOM_ROUTE = "$ADDRESS/api/createRoom"
    const val GET_ROOM_ROUTE = "$ADDRESS/api/getRooms"
    const val JOIN_ROOM_ROUTE = "$ADDRESS/api/joinRoom"

}