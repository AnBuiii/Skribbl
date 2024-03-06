package com.anbui.skribbl.di

import com.anbui.skribbl.core.data.local.SettingManager
import com.anbui.skribbl.core.data.network.StartGameImpl
import com.anbui.skribbl.core.data.network.TestRepositoryImpl
import com.anbui.skribbl.core.repository.SettingRepositoryImpl
import com.anbui.skribbl.core.utils.Constants
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.domain.repository.SettingRepository
import com.anbui.skribbl.domain.repository.StartGameService
import com.anbui.skribbl.domain.repository.TestRepository
import com.anbui.skribbl.feature.createRoom.CreateRoomScreenModel
import com.anbui.skribbl.feature.game.GameScreenModel
import com.anbui.skribbl.feature.selectRoom.SelectRoomScreenModel
import com.anbui.skribbl.feature.start.StartScreenModel
import com.anbui.skribbl.feature.username.UsernameScreenModel
import com.anbui.skribbl.platform.engine
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule(): Module = module {
    // HttpClient
    single<HttpClient> {
        HttpClient(engine) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v("HTTP Client", null, message)
                    }
                }
                level = LogLevel.HEADERS
            }
            install(ContentNegotiation) {
                json()
            }
            install(DefaultRequest) {
                url("http://${Constants.IP_DEVICE_2}:${Constants.PORT}/api/")
            }
        }
    }

    single<DispatcherProvider> {
        object : DispatcherProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
        }
    }


    // Repository
    single<TestRepository> { TestRepositoryImpl(get()) }

    single<StartGameService> { StartGameImpl(get()) }
    single<SettingRepository> { SettingRepositoryImpl(get()) }


    // ViewModel
    single<UsernameScreenModel> {
        UsernameScreenModel()
    }

//    single<SelectRoomScreenModel>{
//        SelectRoomScreenModel()
//    }

    factory { SelectRoomScreenModel() }

    factory { CreateRoomScreenModel() }

    single<StartScreenModel> {
        StartScreenModel(
            settingRepository = get(),
            startGameService = get(),
            dispatcherProvider = get()
        )
    }
    single<GameScreenModel> {
        GameScreenModel(get())
    }

    // Setting
    single<SettingManager> { SettingManager(settings = get()) }
}