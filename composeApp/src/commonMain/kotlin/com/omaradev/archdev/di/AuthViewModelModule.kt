package com.omaradev.archdev.di

import com.omaradev.archdev.ui.viewmodel.AuthViewModel
import com.omaradev.auth_di.platformAuthenticationModule
import com.omaradev.auth_domain.usecase.CheckUserSessionUseCase
import com.omaradev.auth_domain.usecase.ClearUserSessionUseCase
import com.omaradev.auth_domain.usecase.LoginUseCase
import com.omaradev.di.platformDatabaseModule
import com.omaradev.di.platformPreferencesModule
import com.omaradev.di.platformUserAuthenticationModule
import com.omaradev.todo_di.platformTodoModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    factory { CheckUserSessionUseCase(get()) }
    factory { ClearUserSessionUseCase(get()) }
    viewModelOf(::AuthViewModel)
}