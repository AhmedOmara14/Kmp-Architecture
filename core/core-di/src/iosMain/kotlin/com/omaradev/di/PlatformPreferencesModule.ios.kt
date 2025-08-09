package com.omaradev.di

import com.omaradev.preferences.IPreferences
import com.omaradev.preferences.IosPreferences
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual fun platformPreferencesModule() = module {

    single<NSUserDefaults> { NSUserDefaults.standardUserDefaults } // For multiplatform-settings
    single<IPreferences> { IosPreferences(get()) } // Injects Settings into PreferencesImpl

}