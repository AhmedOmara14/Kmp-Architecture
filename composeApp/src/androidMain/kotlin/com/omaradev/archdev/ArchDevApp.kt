package com.omaradev.archdev

import android.app.Application
import com.omaradev.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class ArchDevApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ArchDevApp)
            androidLogger()
        }
    }
}