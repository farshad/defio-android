package io.neoattitude.defio

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DefioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DefioApplication)
            //modules(oauthModule)
        }
    }
}
