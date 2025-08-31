package com.omaradev.auth_ui.login.navigation

import cafe.adriel.voyager.navigator.Navigator
import com.omaradev.auth_ui.register.RegisterScreen

class LoginNavigatorImpl(private val navigator: Navigator) : LoginNavigator {
    override fun navigateToRegister() {
        navigator.push(RegisterScreen)
    }
}