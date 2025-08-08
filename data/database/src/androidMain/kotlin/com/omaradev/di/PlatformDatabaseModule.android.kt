package com.omaradev.di

import com.omaradev.database.AppDatabase
import com.omaradev.database.getDatabaseBuilder
import org.koin.dsl.module


actual fun platformDatabaseModule()= module {
    single { getDatabaseBuilder(get()).build() }
    single { get<AppDatabase>().getUserDao() }
    single { get<AppDatabase>().getTodoDao() }
}