package com.omaradev.auth_di

import com.omaradev.auth_domain.repository.UserRepository
import com.omaradev.data.repository.UserRepositoryImpl
import org.koin.dsl.module

fun platformAuthenticationModule() = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}