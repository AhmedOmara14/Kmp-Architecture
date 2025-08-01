package com.omaradev.preferences

interface IPreferences {
    fun saveInt(key: String, value: Int)
    fun getInt(key: String): Int
    fun saveString(key: String, value: String)
    fun getString(key: String): String?
    fun saveBoolean(key: String, value: Boolean)
    fun getBoolean(key: String): Boolean
    fun clear()
}