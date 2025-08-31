package com.omaradev.archdev.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.omaradev.auth_ui.navigation.AuthNavigation
import org.koin.compose.koinInject

@Composable
fun App() {
    val authNav: AuthNavigation = koinInject()
    Navigator(authNav.login())
}
