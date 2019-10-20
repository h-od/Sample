package com.hughod.app

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.hughod.app.config.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@App)

            modules(modules)
        }
    }
}

class MainActivity : AppCompatActivity(R.layout.activity_main)
