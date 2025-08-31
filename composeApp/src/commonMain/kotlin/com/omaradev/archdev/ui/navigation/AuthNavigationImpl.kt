package com.omaradev.archdev.ui.navigation

import com.omaradev.auth_ui.login.LoginScreen
import com.omaradev.auth_ui.navigation.AuthNavigation
import com.omaradev.todo_ui.home.navigation.TodoNavigation


class AuthNavigationImpl(
    private val todoNav: TodoNavigation
) : AuthNavigation {
    override fun login() = LoginScreen()
    override fun navigateToHome() = todoNav.home()
}