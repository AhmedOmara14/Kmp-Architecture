package com.omaradev.preferences

import android.content.SharedPreferences

class AndroidPreferences(
    val sharedPreferences: SharedPreferences
) : IPreferences {
    override fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
    override fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}