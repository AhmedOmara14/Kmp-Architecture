package com.omaradev.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.omaradev.auth_ui.login.LoginScreenContent
import com.omaradev.auth_ui.login.LoginUiState
import com.omaradev.auth_ui.register.RegisterScreenContent
import com.omaradev.auth_ui.register.RegisterUiState

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreenContent(
        uiState = LoginUiState(
            username = "omara_dev@example.com",
            password = "123456"
        ),
        onEmailChange = {},
        onPasswordChange = {},
        onLogin = {}
    )
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RegisterScreenContent(
        uiState = RegisterUiState(
            password = "123456",
            confirmPassword = "123456",
            name = "omara"
        ),
        onNameChange = {},
        onPasswordChange = {},
        onBackPressure = {}
    )
    
}