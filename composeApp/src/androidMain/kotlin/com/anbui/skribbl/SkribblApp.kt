package com.anbui.skribbl

import android.app.Application
import com.anbui.skribbl.di.KoinInit
import org.koin.core.logger.Level

class SkribblApp : Application() {
    override fun onCreate() {
        super.onCreate()

        KoinInit().init()
    }
}