package com.omaradev.di

import android.content.Context
import android.content.SharedPreferences
import com.omaradev.preferences.AndroidPreferences
import com.omaradev.preferences.IPreferences
import org.koin.dsl.module

actual fun platformPreferencesModule() = module {
    single<SharedPreferences> {
        val context: Context = get()
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    single<IPreferences> {
        AndroidPreferences(get())
    }
}