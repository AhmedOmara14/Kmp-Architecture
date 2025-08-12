package com.omaradev.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.omaradev.auth_ui.login.LoginScreenContent
import com.omaradev.auth_ui.login.LoginUiState

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
