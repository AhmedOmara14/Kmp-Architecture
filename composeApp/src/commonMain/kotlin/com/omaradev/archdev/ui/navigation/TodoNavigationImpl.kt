package com.omaradev.archdev.ui.navigation

import com.omaradev.auth_ui.navigation.AuthNavigation
import com.omaradev.todo_ui.home.HomeScreen
import com.omaradev.todo_ui.home.navigation.TodoNavigation

class TodoNavigationImpl : TodoNavigation {
    private lateinit var authNav: AuthNavigation

    fun setAuthNavigation(authNav: AuthNavigation) {
        this.authNav = authNav
    }

    override fun home() = HomeScreen(
        logout = {}
    )
}