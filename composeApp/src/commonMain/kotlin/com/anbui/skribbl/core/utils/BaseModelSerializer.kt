package com.anbui.skribbl.core.utils

import com.anbui.skribbl.core.data.remote.response.message.Announcement
import com.anbui.skribbl.core.data.remote.response.message.BaseModel
import com.anbui.skribbl.core.data.remote.response.message.ChatMessage
import com.anbui.skribbl.core.data.remote.response.message.ChosenWord
import com.anbui.skribbl.core.data.remote.response.message.Disconnect
import com.anbui.skribbl.core.data.remote.response.message.DrawAction
import com.anbui.skribbl.core.data.remote.response.message.DrawData
import com.anbui.skribbl.core.data.remote.response.message.GameError
import com.anbui.skribbl.core.data.remote.response.message.GameState
import com.anbui.skribbl.core.data.remote.response.message.JoinRoomHandshake
import com.anbui.skribbl.core.data.remote.response.message.NewWords
import com.anbui.skribbl.core.data.remote.response.message.NotBaseModel
import com.anbui.skribbl.core.data.remote.response.message.PhaseChange
import com.anbui.skribbl.core.data.remote.response.message.Ping
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

object BaseSerializerModule {
    private val baseModelSerializerModule = SerializersModule {
        polymorphic(BaseModel::class) {
            subclass(ChatMessage::class)
            subclass(DrawData::class)
            subclass(Announcement::class)
            subclass(JoinRoomHandshake::class)
            subclass(GameError::class)
            subclass(PhaseChange::class)
            subclass(ChosenWord::class)
            subclass(GameState::class)
            subclass(NewWords::class)
            subclass(Ping::class)
            subclass(Disconnect::class)
            subclass(DrawAction::class)
            defaultDeserializer { NotBaseModel.serializer() }
        }
    }

    val baseJson = Json { serializersModule = baseModelSerializerModule }
}
