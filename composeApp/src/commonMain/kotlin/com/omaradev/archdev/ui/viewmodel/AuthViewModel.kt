package com.omaradev.archdev.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.auth_domain.usecase.CheckUserSessionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val checkUserSessionUseCase: CheckUserSessionUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState?>(null)
    val authState: StateFlow<AuthState?> = _authState

    init {
        checkUserSession()
    }

    private fun checkUserSession() {
        viewModelScope.launch {
            val isLoggedIn = checkUserSessionUseCase()
            _authState.value = if (isLoggedIn) {
                AuthState.LoggedIn
            } else {
                AuthState.LoggedOut
            }
        }
    }
}

sealed class AuthState {
    object LoggedIn : AuthState()
    object LoggedOut : AuthState()
}
