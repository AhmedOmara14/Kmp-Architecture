package com.omaradev.archdev

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.omaradev.preferences.IPreferences

class HomeViewModel(
    val preferences: IPreferences
): ViewModel() {

    var token by mutableStateOf(preferences.getString("auth_token"))
        private set

    fun saveToken(newToken: String) {
        preferences.saveString("auth_token", newToken)
        token = newToken
    }

}