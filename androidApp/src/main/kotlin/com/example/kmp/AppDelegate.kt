package com.example.kmp

import android.app.Application
import com.example.kmp.koin.config.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class AppDelegate: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AppDelegate)
            androidLogger(Level.ERROR)
        }
    }

}