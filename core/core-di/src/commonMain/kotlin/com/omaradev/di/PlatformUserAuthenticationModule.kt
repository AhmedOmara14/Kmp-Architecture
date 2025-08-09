package com.omaradev.di

import com.omaradev.core_data.repository.AuthorizedUserRepositoryImplementation
import com.omaradev.core_domain.repository.AuthorizedUserRepository
import org.koin.dsl.module

fun platformUserAuthenticationModule() = module {
    single<AuthorizedUserRepository> { AuthorizedUserRepositoryImplementation(get(),get()) }
}