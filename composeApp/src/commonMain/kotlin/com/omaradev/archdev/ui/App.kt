package com.omaradev.archdev.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.Navigator
import com.omaradev.archdev.ui.viewmodel.AuthState
import com.omaradev.archdev.ui.viewmodel.AuthViewModel
import com.omaradev.auth_ui.login.LoginScreen
import com.omaradev.todo_ui.home.HomeScreen
import org.koin.compose.koinInject

@Composable
fun App(authViewModel: AuthViewModel = koinInject()) {
    val state by authViewModel.authState.collectAsState()
    when (state) {
        AuthState.LoggedIn -> Navigator(HomeScreen(
            logout = {
                authViewModel.logout()
            }
        ))
        AuthState.LoggedOut -> Navigator(LoginScreen())
        else -> {}
    }
}
