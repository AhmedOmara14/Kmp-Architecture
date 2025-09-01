package com.omaradev.auth_ui.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.omaradev.auth_ui.navigation.AuthNavigation
import com.omaradev.auth_ui.register.navigation.RegisterNavigatorImpl
import com.omaradev.auth_ui.resources.Res
import com.omaradev.auth_ui.resources.already_have_an_account
import com.omaradev.auth_ui.resources.confirm_password
import com.omaradev.auth_ui.resources.name
import com.omaradev.auth_ui.resources.password
import com.omaradev.auth_ui.resources.sign_up
import com.omaradev.core_ui.ErrorView
import com.omaradev.core_ui.components.AppButton
import com.omaradev.core_ui.components.AppTextInput
import com.omaradev.core_ui.theme.ColorPrimary
import com.omaradev.core_ui.theme.appTypography
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

object RegisterScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val registerNavigator = remember { RegisterNavigatorImpl(navigator) }
        val authNav: AuthNavigation = koinInject()

        RegisterScreen(
            navigateUp = {
                navigator.pop()
            },
            goToHomePage = {
                authNav.navigateToHome()?.let { navigator.push(it) }
            },
            goToLoginPage = {
                registerNavigator.navigateToLogin()
            }
        )
    }
}

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = koinViewModel(),
    navigateUp: () -> Unit,
    goToHomePage: () -> Unit,
    goToLoginPage: () -> Unit
) {
    val state = registerViewModel.uiState.collectAsState()

    LaunchedEffect(state.value) {
        if (state.value.isLoggedIn)
            goToHomePage()
    }

    RegisterScreenContent(
        uiState = state.value,
        onNameChange = registerViewModel::onUsernameChanged,
        onPasswordChange = registerViewModel::onPasswordChanged,
        onConfirmPasswordChange = registerViewModel::onConfirmPassword,
        onSignUp = registerViewModel::register,
        onSignIn = goToLoginPage,
        onBackPressure = navigateUp
    )
}

@Composable
fun RegisterScreenContent(
    onBackPressure: () -> Unit,
    uiState: RegisterUiState = RegisterUiState(),
    onNameChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onConfirmPasswordChange: (String) -> Unit = {},
    onSignUp: () -> Unit = {},
    onSignIn: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.padding(horizontal = 16.dp).clickable(onClick = {
                onBackPressure()
            })
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.sign_up),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = ColorPrimary,
                style = appTypography().headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppTextInput(
                defaultValue = uiState.name,
                hint = stringResource(Res.string.name),
                onValueChange = onNameChange,
                errorString = uiState.nameError
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppTextInput(
                defaultValue = uiState.password,
                hint = stringResource(Res.string.password),
                onValueChange = onPasswordChange,
                errorString = uiState.passwordError,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppTextInput(
                defaultValue = uiState.confirmPassword,
                hint = stringResource(Res.string.confirm_password),
                onValueChange = onConfirmPasswordChange,
                errorString = uiState.confirmPasswordError,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                value = stringResource(Res.string.sign_up),
                onClick = onSignUp,
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
                text = stringResource(Res.string.already_have_an_account),
                modifier = Modifier.clickable(
                    onClick = {
                        onSignIn()
                    }),
                textAlign = TextAlign.Center,
                color = ColorPrimary,
                style = appTypography().bodySmall,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}