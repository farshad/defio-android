package io.neoattitude.defio

import android.app.Application
import io.neoattitude.defio.di.databaseModule
import io.neoattitude.defio.di.networkModule
import io.neoattitude.defio.di.repositoryModule
import io.neoattitude.defio.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DefioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DefioApplication)
            modules(listOf(databaseModule, networkModule, repositoryModule, viewModelModule))
        }
    }
}
