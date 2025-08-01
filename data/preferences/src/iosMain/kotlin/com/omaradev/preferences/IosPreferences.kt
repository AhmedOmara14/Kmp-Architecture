package com.omaradev.preferences

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

class IosPreferences(settingDelete: NSUserDefaults) : IPreferences {

    val settings: Settings = NSUserDefaultsSettings(settingDelete)

    override fun saveInt(key: String, value: Int) {
        settings.putInt(key, value)
    }

    override fun getInt(key: String): Int {
        return settings.getInt(key, 0)
    }

    override fun saveString(key: String, value: String) {
        settings.putString(key, value)
    }

    override fun getString(key: String): String {
        return settings.getString(key, "")
    }

    override fun saveBoolean(key: String, value: Boolean) {
        settings.putBoolean(key, value)
    }

    override fun getBoolean(key: String): Boolean {
        return settings.getBoolean(key, false)
    }

    override fun clear() {
        settings.clear()
    }
}