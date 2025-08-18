package com.omaradev.auth_ui.register

import org.jetbrains.compose.resources.StringResource

data class RegisterUiState(
    val name: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null,
    val nameError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
)
