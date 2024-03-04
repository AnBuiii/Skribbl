package com.anbui.skribbl.di

import com.anbui.skribbl.core.data.network.StartGameImpl
import com.anbui.skribbl.core.data.network.TestRepositoryImpl
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.core.utils.DispatcherProviderImpl
import com.anbui.skribbl.domain.repository.StartGameService
import com.anbui.skribbl.domain.repository.TestRepository
import com.anbui.skribbl.feature.game.GameScreenModel
import com.anbui.skribbl.feature.start.StartScreenModel
import com.anbui.skribbl.platform.engine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule(): Module = module {
    // HttpClient
    single<HttpClient> {
        HttpClient(engine) {
//            this.
            install(ContentNegotiation) {
                json()
            }
        }
    }

    // Dispatcher
    single<DispatcherProvider> {
        DispatcherProviderImpl()
    }


    // Repository
    single<TestRepository> { TestRepositoryImpl(get()) }

    single<StartGameService> { StartGameImpl(get()) }


    // ViewModel
    single<StartScreenModel> {
        StartScreenModel(get(), get())
    }
    single<GameScreenModel> {
        GameScreenModel(get())
    }

    // ViewModel
}