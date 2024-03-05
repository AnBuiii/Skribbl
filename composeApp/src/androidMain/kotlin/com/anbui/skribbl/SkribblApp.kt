package com.anbui.skribbl

import android.app.Application
import com.anbui.skribbl.di.KoinInit
import com.russhwolf.settings.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class SkribblApp : Application() {
    override fun onCreate() {
        super.onCreate()

        KoinInit().init {
            androidContext(androidContext = this@SkribblApp)
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        }
    }
}