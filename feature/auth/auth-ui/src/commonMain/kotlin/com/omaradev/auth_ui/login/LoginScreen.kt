package com.omaradev.auth_ui.login

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.unit.dp
import com.omaradev.auth_ui.resources.Res
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
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsState()

    LoginScreenContent(
        state,
        onEmailChange = viewModel::onUsernameChanged,
        onPasswordChange = viewModel::onPasswordChanged,
        onLogin = viewModel::login
    )
}

@Composable
fun LoginScreenContent(
    uiState: LoginUiState = LoginUiState(),
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onLogin: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(
                Res.string.welcome
            ),
            color = ColorPrimary,
            style = appTypography().headlineMedium,
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
            onValueChange = {
                onPasswordChange(it)
            }
        )

        Spacer(Modifier.height(16.dp))

        AppButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            value = stringResource(Res.string.login),
            onClick = onLogin,
            loading = uiState.isLoading
        )

        AnimatedVisibility(uiState.errorMessage !=null){
            ErrorView(
                errorId = uiState.errorMessage ?: "",
                modifier = Modifier.fillMaxWidth(),
                isError = true
            )
        }


    }
}