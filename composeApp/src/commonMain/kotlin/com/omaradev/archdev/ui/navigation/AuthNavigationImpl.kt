package com.omaradev.archdev.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.omaradev.auth_ui.login.LoginScreen
import com.omaradev.auth_ui.navigation.AuthNavigation
import com.omaradev.todo_ui.home.navigation.TodoNavigation


class AuthNavigationImpl : AuthNavigation {
    private lateinit var todoNav: TodoNavigation

    fun setTodoNavigation(todoNav: TodoNavigation) {
        this.todoNav = todoNav
    }

    override fun login() = LoginScreen()


    override fun navigateToHome() = todoNav.home()
}