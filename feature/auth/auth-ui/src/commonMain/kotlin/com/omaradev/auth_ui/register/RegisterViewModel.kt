package com.omaradev.auth_ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.auth_domain.result.LoginResult
import com.omaradev.auth_domain.result.RegistrationResult
import com.omaradev.auth_domain.usecase.LoginUseCase
import com.omaradev.auth_domain.usecase.RegisterUseCase
import com.omaradev.auth_ui.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onUsernameChanged(newUsername: String) {
        _uiState.value = _uiState.value.copy(name = newUsername)
    }

    fun onPasswordChanged(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun onConfirmPassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = newPassword)
    }

    fun register() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(isLoading = true, errorMessage = null, passwordError = null, confirmPasswordError = null, nameError = null)

        val nameError = validateName(currentState.name)
        val passwordError = validatePassword(currentState.password)
        val confirmError =
            validateConfirmPassword(currentState.password, currentState.confirmPassword)

        if (nameError != null || passwordError != null || confirmError != null) {
            _uiState.value = currentState.copy(
                nameError = nameError,
                passwordError = passwordError,
                confirmPasswordError = confirmError,
                isLoading = false
            )
            return
        }

        viewModelScope.launch {
            val result = registerUseCase(currentState.name, currentState.password)
            _uiState.value = when (result) {
                RegistrationResult.SUCCESS -> {
                    _uiState.value.copy(
                        isLoading = false,
                        isLoggedIn = true
                    )
                }

                RegistrationResult.FAILURE -> {
                    _uiState.value.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        errorMessage = "SomeThing Went Wrong"
                    )
                }

                RegistrationResult.USER_ALREADY_REGISTERED -> {
                    _uiState.value.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        errorMessage = "User Already Registered"
                    )
                }
            }
        }
    }

    private fun validateName(name: String): String? {
        return when {
            name.isBlank() -> "Username cannot be empty"
            name.length < 3 -> "Username must be at least 3 characters"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password cannot be empty"
            password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }
    }

    private fun validateConfirmPassword(password: String, confirmPassword: String): String? {
        return when {
            confirmPassword.isBlank() -> "Confirm Password cannot be empty"
            confirmPassword != password -> "Passwords do not match"
            else -> null
        }
    }
}