package com.example.necocomposeapp.application

import android.app.Application
import com.example.necocomposeapp.di.appModule
import com.example.necocomposeapp.di.networkModule
import com.example.necocomposeapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class App : Application(), KoinComponent {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(instance)
            modules(appModule, networkModule, viewModelModule)
        }

    }

}