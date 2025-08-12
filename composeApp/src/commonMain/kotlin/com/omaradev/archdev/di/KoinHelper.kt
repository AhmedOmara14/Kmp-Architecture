package com.omaradev.archdev.di

import com.omaradev.auth_di.platformAuthenticationModule
import com.omaradev.di.platformDatabaseModule
import com.omaradev.di.platformPreferencesModule
import com.omaradev.di.platformUserAuthenticationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformPreferencesModule(),
            platformDatabaseModule(),
            platformAuthenticationModule(),
            platformUserAuthenticationModule()
        )
    }
}
