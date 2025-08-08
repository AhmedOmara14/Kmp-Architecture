package com.omaradev.di

import com.omaradev.database.AppDatabase
import com.omaradev.database.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformDatabaseModule()= module {
    single<AppDatabase> { getDatabaseBuilder() }
    single { get<AppDatabase>().getTodoDao() }
    single { get<AppDatabase>().getUserDao() }
}

