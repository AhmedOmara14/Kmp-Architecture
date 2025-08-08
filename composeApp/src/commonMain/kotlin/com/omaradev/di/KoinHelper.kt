package com.omaradev.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    println("Starting Koin...")
    startKoin {
        config?.invoke(this)
        modules(sharedModules,platformPreferencesModule(),platformDatabaseModule())
    }
}
