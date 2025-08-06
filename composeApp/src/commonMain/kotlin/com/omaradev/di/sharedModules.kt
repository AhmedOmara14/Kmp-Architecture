package com.omaradev.di

import com.omaradev.archdev.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModules = module {
    viewModelOf(::HomeViewModel)
}