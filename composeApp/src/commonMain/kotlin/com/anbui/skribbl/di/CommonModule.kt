package com.anbui.skribbl.di

import com.anbui.skribbl.feeature.game.GameScreenModel
import com.anbui.skribbl.feeature.start.StartScreenModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule(): Module = module {
    single<StartScreenModel> {
        StartScreenModel()
    }
    single<GameScreenModel> {
        GameScreenModel()
    }
}