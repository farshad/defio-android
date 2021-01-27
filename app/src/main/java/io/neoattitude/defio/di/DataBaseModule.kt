package io.neoattitude.defio.di

import android.app.Application
import io.neoattitude.defio.DefioDatabase
import io.neoattitude.defio.data.dao.TokenDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): DefioDatabase =
        DefioDatabase.getDatabase(application)

    fun provideTokenDao(database: DefioDatabase): TokenDao = database.tokenDao()

    single { provideDatabase(androidApplication()) }
    single { provideTokenDao(get()) }
}
