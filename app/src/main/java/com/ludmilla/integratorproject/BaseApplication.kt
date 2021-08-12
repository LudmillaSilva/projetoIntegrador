package com.ludmilla.integratorproject

import android.app.Application
import com.ludmilla.integratorproject.data.di.daoModule
import com.ludmilla.integratorproject.data.di.repositoryModule
import com.ludmilla.integratorproject.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(viewModelModule)
            modules(repositoryModule)
            modules(daoModule)
        }
    }
}