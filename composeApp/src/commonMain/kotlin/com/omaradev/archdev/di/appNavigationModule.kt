package com.omaradev.archdev.di

import com.omaradev.archdev.ui.navigation.AuthNavigationImpl
import com.omaradev.archdev.ui.navigation.TodoNavigationImpl
import com.omaradev.auth_ui.navigation.AuthNavigation
import com.omaradev.todo_ui.home.navigation.TodoNavigation
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin

val appNavigationModule = module {
    single<AuthNavigationImpl> { AuthNavigationImpl() }
    single<TodoNavigationImpl> { TodoNavigationImpl() }

    single<AuthNavigation> {
        get<AuthNavigationImpl>().apply {
            setTodoNavigation(get<TodoNavigationImpl>())
        }
    }
    single<TodoNavigation> {
        get<TodoNavigationImpl>().apply {
            setAuthNavigation(get<AuthNavigationImpl>())
        }
    }
}
