package com.omaradev.auth_ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.omaradev.auth_ui.resources.Res
import com.omaradev.auth_ui.resources.don_t_have_an_account
import com.omaradev.auth_ui.resources.login
import com.omaradev.auth_ui.resources.name
import com.omaradev.auth_ui.resources.password
import com.omaradev.auth_ui.resources.welcome
import com.omaradev.core_ui.ErrorView
import com.omaradev.core_ui.components.AppButton
import com.omaradev.core_ui.components.AppTextInput
import com.omaradev.core_ui.theme.ColorPrimary
import com.omaradev.core_ui.theme.appTypography
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel(), onSignUp: () -> Unit = {}) {
    val state by viewModel.uiState.collectAsState()

    LoginScreenContent(
        uiState = state,
        onEmailChange = viewModel::onUsernameChanged,
        onPasswordChange = viewModel::onPasswordChanged,
        onLogin = viewModel::login,
        onSignUp = onSignUp
    )
}

@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onSignUp: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(Res.string.welcome),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = ColorPrimary,
            style = appTypography().headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppTextInput(
            defaultValue = uiState.username,
            hint = stringResource(Res.string.name),
            onValueChange = onEmailChange
        )

        Spacer(modifier = Modifier.height(8.dp))

        AppTextInput(
            defaultValue = uiState.password,
            hint = stringResource(Res.string.password),
            onValueChange = onPasswordChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = stringResource(Res.string.login),
            onClick = onLogin,
            loading = uiState.isLoading
        )

        Spacer(modifier = Modifier.height(8.dp))

        AnimatedVisibility(visible = uiState.errorMessage != null) {
            ErrorView(
                errorId = uiState.errorMessage.orEmpty(),
                modifier = Modifier.fillMaxWidth(),
                isError = true
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(Res.string.don_t_have_an_account),
            modifier = Modifier.fillMaxWidth().clickable(onClick = {
                onSignUp()
            }),
            textAlign = TextAlign.Center,
            color = ColorPrimary,
            style = appTypography().bodySmall,
            textDecoration = TextDecoration.Underline
        )
    }
}
