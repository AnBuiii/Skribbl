package com.anbui.skribbl.domain.model

data class Room(
    val roomName: String,
    val playerCount: Int,
    val roomSize: Int
) {
    enum class Phase {
        WAITING_FOR_PLAYERS,
        WAITING_FOR_START,
        NEW_ROUND,
        GAME_RUNNING,
        SHOW_WORD
    }
}

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
