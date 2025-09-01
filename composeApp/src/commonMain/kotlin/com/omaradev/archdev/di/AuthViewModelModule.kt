package com.omaradev.archdev.di

import com.omaradev.archdev.ui.viewmodel.AuthViewModel
import com.omaradev.auth_domain.usecase.CheckUserSessionUseCase
import com.omaradev.auth_domain.usecase.LoginUseCase
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    factory { CheckUserSessionUseCase(get()) }
    viewModelOf(::AuthViewModel)
}