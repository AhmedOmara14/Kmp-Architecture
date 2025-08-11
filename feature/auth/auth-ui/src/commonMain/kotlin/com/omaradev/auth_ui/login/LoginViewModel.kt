package com.omaradev.auth_ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.auth_domain.result.LoginResult
import com.omaradev.auth_domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onUsernameChanged(newUsername: String) {
        _uiState.value = _uiState.value.copy(username = newUsername)
    }

    fun onPasswordChanged(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun login() {
        val currentState = _uiState.value

        _uiState.value = currentState.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            val result = loginUseCase(currentState.username, currentState.password)
            _uiState.value = when (result) {
                LoginResult.SUCCESS -> _uiState.value.copy(
                    isLoading = false,
                    isLoggedIn = true
                )

                LoginResult.FAILURE -> _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Invalid username or password"
                )
            }
        }
    }

}