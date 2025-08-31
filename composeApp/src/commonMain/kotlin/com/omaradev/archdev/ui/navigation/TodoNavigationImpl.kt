package com.omaradev.archdev.ui.navigation

import cafe.adriel.voyager.core.screen.Screen
import com.omaradev.todo_ui.home.HomeScreen
import com.omaradev.todo_ui.home.navigation.TodoNavigation

class TodoNavigationImpl : TodoNavigation {
    override fun home() = HomeScreen()
}
