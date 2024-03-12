package com.anbui.skribbl.di

import com.anbui.skribbl.core.data.local.SettingManager
import com.anbui.skribbl.core.data.remote.repository.StartGameImpl
import com.anbui.skribbl.core.data.remote.websocket.SocketServiceImpl
import com.anbui.skribbl.core.repository.SettingRepositoryImpl
import com.anbui.skribbl.core.repository.SnackBarRepositoryImpl
import com.anbui.skribbl.core.utils.APIConstant
import com.anbui.skribbl.core.utils.BaseSerializerModule
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.core.utils.SessionInterceptorPlugin
import com.anbui.skribbl.domain.repository.SettingRepository
import com.anbui.skribbl.domain.repository.SnackBarRepository
import com.anbui.skribbl.domain.repository.SocketService
import com.anbui.skribbl.domain.repository.StartGameService
import com.anbui.skribbl.feature.createRoom.CreateRoomScreenModel
import com.anbui.skribbl.feature.game.GameScreenModel
import com.anbui.skribbl.feature.selectRoom.SelectRoomScreenModel
import com.anbui.skribbl.feature.username.UsernameScreenModel
import com.anbui.skribbl.main.MainScreenModel
import com.anbui.skribbl.platform.engine
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.runBlocking
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
                BaseSerializerModule.baseJson
            }
            install(DefaultRequest) {
                url {
                    host = APIConstant.HOST_DEVICE_1
                    port = APIConstant.PORT
                }
//                contentType(ContentType.Application.Json)
            }

            install(WebSockets) {
//                pingInterval = 15_000
            }

            install(SessionInterceptorPlugin(get()))
        }
    }

    single<String> {
        runBlocking {
            val settingRepository = get<SettingRepository>()
            settingRepository.getClientId()
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

    single<StartGameService> { StartGameImpl(get()) }
    single<SettingRepository> { SettingRepositoryImpl(get()) }
    single<SnackBarRepository> { SnackBarRepositoryImpl() }

    // GameService
    single<SocketService> { SocketServiceImpl(get(), get(), get()) }

    // ViewModel
    single<UsernameScreenModel> {
        UsernameScreenModel(get())
    }
    factory { SelectRoomScreenModel(get(), get(), get()) }

    factory { CreateRoomScreenModel(get(), get()) }

    factory { MainScreenModel(get()) }

    factory<GameScreenModel> {
        GameScreenModel(get(), get(), get(), get())
    }

    // Setting
    single<SettingManager> { SettingManager(settings = get()) }
}
