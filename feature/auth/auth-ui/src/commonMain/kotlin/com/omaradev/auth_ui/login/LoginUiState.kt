package com.omaradev.auth_ui.login

import org.jetbrains.compose.resources.StringResource

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null
)