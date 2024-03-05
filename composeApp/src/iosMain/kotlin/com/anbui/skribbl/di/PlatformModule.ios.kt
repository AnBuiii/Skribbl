package com.anbui.skribbl.di

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual fun platformModule() = module {
    single<Settings> {
        val delegate = NSUserDefaults.standardUserDefaults
        NSUserDefaultsSettings(delegate)
    }
}