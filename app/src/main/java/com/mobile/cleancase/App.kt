package com.mobile.cleancase

import android.app.Application
import com.mobile.cleancase.data.di.dataModule
import com.mobile.cleancase.domain.di.domainModule
import com.mobile.cleancase.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        val appModules = dataModule + domainModule + viewModelModule
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModules)
        }
    }
}