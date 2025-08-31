package com.omaradev.archdev.di

import com.omaradev.archdev.ui.navigation.AuthNavigationImpl
import com.omaradev.archdev.ui.navigation.TodoNavigationImpl
import com.omaradev.auth_ui.navigation.AuthNavigation
import com.omaradev.todo_ui.home.navigation.TodoNavigation
import org.koin.dsl.module

val appNavigationModule = module {
    single<TodoNavigation> { TodoNavigationImpl() }
    single<AuthNavigation> { AuthNavigationImpl(get()) }
}
