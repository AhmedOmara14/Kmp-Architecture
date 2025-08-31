package com.omaradev.auth_ui.register.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.omaradev.auth_ui.login.LoginScreen

class RegisterNavigatorImpl(private val navigator: Navigator) : RegisterNavigator {
    override fun navigateToLogin(): Screen {
        navigator.push(LoginScreen())
    }
}