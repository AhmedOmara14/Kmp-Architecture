package com.omaradev.auth_di

import com.omaradev.auth_ui.login.LoginViewModel
import com.omaradev.auth_domain.repository.UserRepository
import com.omaradev.auth_domain.usecase.LoginUseCase
import com.omaradev.auth_domain.usecase.RegisterUseCase
import com.omaradev.data.repository.UserRepositoryImpl
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun platformAuthenticationModule() = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    viewModelOf(::LoginViewModel)
}