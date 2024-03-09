package com.anbui.skribbl.domain.model

data class Room(
    val roomName: String,
    val playerCount: Int,
    val roomSize: Int
)

val mockRooms = listOf(
    Room(
        "some name",
        3,
        8
    ),
    Room(
        "some name",
        3,
        8
    ),
    Room(
        "some name",
        3,
        8
    )
)
