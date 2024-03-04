package com.anbui.skribbl.di

import com.anbui.skribbl.core.data.network.TestRepositoryImpl
import com.anbui.skribbl.core.utils.DispatcherProvider
import com.anbui.skribbl.core.utils.DispatcherProviderImpl
import com.anbui.skribbl.domain.repository.TestRepository
import com.anbui.skribbl.feature.game.GameScreenModel
import com.anbui.skribbl.feature.start.StartScreenModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule(): Module = module {

    single<HttpClient> {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single<DispatcherProvider> {
        DispatcherProviderImpl()
    }

    single<TestRepository> { TestRepositoryImpl(get()) }

    single<StartScreenModel> {
        StartScreenModel(get(), get())
    }
    single<GameScreenModel> {
        GameScreenModel()
    }
}