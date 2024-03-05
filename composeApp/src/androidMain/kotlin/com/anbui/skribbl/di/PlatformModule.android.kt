package com.anbui.skribbl.di

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<Settings> {
        val context: Context = get()
        val delegate = context.getSharedPreferences("skribbl_preferences", Context.MODE_PRIVATE)
        SharedPreferencesSettings(delegate)
    }
}
